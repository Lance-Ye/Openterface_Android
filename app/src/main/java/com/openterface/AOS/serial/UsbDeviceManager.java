/**
* @Title: UsbDeviceManager
* @Package com.openterface.AOS.serial
* @Description:
 * ========================================================================== *
 *                                                                            *
 *    This file is part of the Openterface Mini KVM App Android version       *
 *                                                                            *
 *    Copyright (C) 2024   <info@openterface.com>                             *
 *                                                                            *
 *    This program is free software: you can redistribute it and/or modify    *
 *    it under the terms of the GNU General Public License as published by    *
 *    the Free Software Foundation version 3.                                 *
 *                                                                            *
 *    This program is distributed in the hope that it will be useful, but     *
 *    WITHOUT ANY WARRANTY; without even the implied warranty of              *
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU        *
 *    General Public License for more details.                                *
 *                                                                            *
 *    You should have received a copy of the GNU General Public License       *
 *    along with this program. If not, see <http://www.gnu.org/licenses/>.    *
 *                                                                            *
 * ========================================================================== *
*/
package com.openterface.AOS.serial;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import com.openterface.AOS.R;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.driver.Ch34xSerialDriver;
import com.hoho.android.usbserial.driver.ProbeTable;
import android.hardware.usb.UsbDeviceConnection;
import android.widget.TextView;
import android.os.Build;

import timber.log.Timber;

public class UsbDeviceManager {
    private static final String TAG = UsbDeviceManager.class.getSimpleName();

    private UsbManager usbManager;
    public static UsbSerialPort port;
    public static UsbSerialDriver driver;
    private HandlerThread mSerialThread;
    private Handler mSerialAsyncHandler;
    private Context context;
    private boolean isReading = false;
    private TextView tvReceivedData;

    private static final int WRITE_WAIT_MILLIS = 2000;
    private static final int READ_WAIT_MILLIS = 2000;
    private static final String ACTION_USB_PERMISSION = "com.example.openterface.USB_PERMISSION";
    private UsbDeviceManager usbDeviceManager;

    private static final int MAX_RETRY_COUNT = 3;
    private static final int RETRY_DELAY_MS = 1000;
    private int retryCount = 0;
    private boolean isConnected = false;

    public interface OnDataReadListener {
        void onDataRead();
    }

    private OnDataReadListener onDataReadListener;

    public void setOnDataReadListener(OnDataReadListener listener) {
        this.onDataReadListener = listener;
    }

    public interface OnConnectionStateListener {
        void onConnected();
        void onDisconnected();
        void onError(String error);
    }

    private OnConnectionStateListener connectionStateListener;

    public void setOnConnectionStateListener(OnConnectionStateListener listener) {
        this.connectionStateListener = listener;
    }

//    public UsbDeviceManager(Context context, TextView tvReceivedData) {
//        this.context = context;
//        this.tvReceivedData = tvReceivedData;
//    }
//
//    public void registerReceiver() {
//        context.registerReceiver(usbReceiver, new IntentFilter("com.android.example.USB_PERMISSION"));
//    }
//
//    public void unregisterReceiver() {
//        context.unregisterReceiver(usbReceiver);
//    }

    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive data successful");
            init();
//            handleUsbDevice(intent);
        }
    };

    public void handleUsbDevice(Intent intent) {
        if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
            Log.d(TAG, "handleUsbDevice data successful");
//            init();
        }
    }

    public UsbDeviceManager(Context context, UsbManager usbManager) {
        this.context = context;
        this.usbManager = usbManager;
        mSerialThread = new HandlerThread("Serial");
        mSerialThread.start();
        mSerialAsyncHandler = new Handler(mSerialThread.getLooper());
    }

    public void init() {
        mSerialAsyncHandler.post(() -> {
            ProbeTable customTable = new ProbeTable();
            customTable.addProduct(0x1A86, 0x7523, Ch34xSerialDriver.class);
            UsbSerialProber prober = new UsbSerialProber(customTable);
            List<UsbSerialDriver> availableDrivers = prober.findAllDrivers(usbManager);
            if (availableDrivers.isEmpty()) {
                return;
            }
            driver = availableDrivers.get(0);
            Log.d("serial", "find available drivers:" + driver.getDevice());
            requestUsbPermission(driver.getDevice());
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.registerReceiver(usbReceiver, filter, Context.RECEIVER_EXPORTED);
        } else {
            context.registerReceiver(usbReceiver, filter);
        }
    }

    public void release() {
        try {
            Timber.tag(TAG).d("Releasing USB resources");
            isReading = false;
            isConnected = false;

            if (context != null) {
                context.unregisterReceiver(usbReceiver);
                Timber.tag(TAG).i("USB receiver unregistered");
            }

            if (port != null) {
                try {
                    port.close();
                    Timber.tag(TAG).i("USB port closed successfully");
                } catch (IOException e) {
                    Timber.tag(TAG).e(e, "Error closing USB port");
                }
                port = null;
            }

            if (driver != null) {
                driver = null;
                Timber.tag(TAG).i("USB driver released");
            }

            if (mSerialThread != null) {
                mSerialThread.quitSafely();
                try {
                    mSerialThread.join();
                    Timber.tag(TAG).i("Serial thread terminated");
                } catch (InterruptedException e) {
                    Timber.tag(TAG).e(e, "Error joining serial thread");
                }
            }

            if (mSerialAsyncHandler != null) {
                mSerialAsyncHandler.removeCallbacksAndMessages(null);
            }

        } catch (Exception e) {
//            Timber.tag(TAG).e(e, "Error during USB resource release");
        }
    }

    private void requestUsbPermission(UsbDevice serialDevice) {
        PendingIntent permissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_IMMUTABLE);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        context.registerReceiver(usbReceiver, filter, Context.RECEIVER_EXPORTED);
        usbManager.requestPermission(serialDevice, permissionIntent);
        Timber.tag(TAG).i("Requesting USB permission for device: %s", serialDevice.getDeviceName());

        UsbDeviceConnection connection = usbManager.openDevice(serialDevice);
        if (connection != null) {
            Timber.tag(TAG).i("USB device opened successfully: %s", serialDevice.getDeviceName());
            // Proceed with serialDevice communication
            // ...
            port = driver.getPorts().get(0); // Most serialDevices have just one port (port 0)
            try {
                port.open(connection);
                port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
                Timber.tag(TAG).i("USB port opened and configured successfully");
                startReading();
            } catch (Exception e) {
                Timber.tag(TAG).e(e, "Failed to open USB port");
            }
        } else {
            Timber.tag(TAG).e("Failed to open USB device: %s", serialDevice.getDeviceName());
        }
    }

    private void startReading() {
        isReading = true;
        mSerialAsyncHandler.post(() -> {
            byte[] buffer = new byte[1024];
            while (isReading) {
                try {
                    int numBytesRead = port.read(buffer, 5);
                    if (numBytesRead > 0) {
                        StringBuilder allReadData = new StringBuilder();
                        for (int i = 0; i < numBytesRead; i++) {
                            allReadData.append(String.format("%02X ", buffer[i]));
                        }
                        Log.d(TAG, "Read data: " + allReadData.toString().trim());

                        if (onDataReadListener != null) {
                            onDataReadListener.onDataRead();
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error reading from port", e);
                    handleConnectionError(e);
                    break;
                }
            }
        });
    }

    private void handleConnectionError(Exception e) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            Log.d(TAG, "Retrying connection, attempt " + retryCount);
            mSerialAsyncHandler.postDelayed(() -> {
                try {
                    if (port != null) {
                        port.close();
                    }
                    init();
                } catch (Exception ex) {
                    Log.e(TAG, "Retry failed", ex);
                }
            }, RETRY_DELAY_MS);
        } else {
            isConnected = false;
            if (connectionStateListener != null) {
                connectionStateListener.onError("Failed to connect after " + MAX_RETRY_COUNT + " attempts");
            }
        }
    }

    public void stopReading() {
        isReading = false;
        if (mSerialAsyncHandler != null) {
            mSerialAsyncHandler.removeCallbacksAndMessages(null);
        }
    }
}