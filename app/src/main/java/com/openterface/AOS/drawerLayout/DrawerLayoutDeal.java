package com.openterface.AOS.drawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openterface.AOS.R;
import com.openterface.AOS.activity.MainActivity;
import com.openterface.AOS.serial.CustomTouchListener;

public class DrawerLayoutDeal {
    private final MainActivity activity;
    private final Context context;
    private final FloatingActionButton set_up_button;
    private final DrawerLayout drawer_layout;

    private final Button Abs_ctrl_default_button;
    private final Drawable Abs_ctrl_default_button_drawable;

    private final Button Abs_ctrl_drag_button;
    private final Drawable Abs_ctrl_drag_button_drawable;

    private final Button Rel_ctrl_button;
    private final Drawable Rel_ctrl_button_drawable;

    private boolean KeyMouse_state = false;
    private boolean keyMouseAbsCtrlState = false;
    private final boolean mIsRecording = false;

    private final Button action_device;
    private final Button action_safely_eject;
    private final Button action_control;
    private final Button action_video_format;
    private final Button action_rotate_90_CW;
    private final Button action_rotate_90_CCW;
    private final Button action_flip_horizontally;
    private final Button action_flip_vertically;
    private final Button ScreenHost_Picture;
    private final Button Recording_Video;
    private final Button Close_DrawLayout;

    public DrawerLayoutDeal(MainActivity activity) {
        this.activity = activity;
        this.context = activity;
        set_up_button = activity.findViewById(R.id.set_up_button);
        drawer_layout = activity.findViewById(R.id.drawer_layout);

        Abs_ctrl_default_button = activity.findViewById(R.id.Abs_ctrl_default_button);
        Abs_ctrl_default_button_drawable = Abs_ctrl_default_button.getCompoundDrawables()[1];
        Abs_ctrl_drag_button = activity.findViewById(R.id.Abs_ctrl_drag_button);
        Abs_ctrl_drag_button_drawable = Abs_ctrl_drag_button.getCompoundDrawables()[1];
        Rel_ctrl_button = activity.findViewById(R.id.Rel_ctrl_button);
        Rel_ctrl_button_drawable = Rel_ctrl_button.getCompoundDrawables()[1];

        Rel_ctrl_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_IN);
        Rel_ctrl_button.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));

        action_device = activity.findViewById(R.id.action_device);
        action_safely_eject = activity.findViewById(R.id.action_safely_eject);
        action_control = activity.findViewById(R.id.action_control);
        action_video_format = activity.findViewById(R.id.action_video_format);
        action_rotate_90_CW = activity.findViewById(R.id.action_rotate_90_CW);
        action_rotate_90_CCW = activity.findViewById(R.id.action_rotate_90_CCW);
        action_flip_horizontally = activity.findViewById(R.id.action_flip_horizontally);
        action_flip_vertically = activity.findViewById(R.id.action_flip_vertically);
        ScreenHost_Picture = activity.findViewById(R.id.ScreenHost_Picture);
        Recording_Video = activity.findViewById(R.id.Recording_Video);

        Close_DrawLayout = activity.findViewById(R.id.Close_DrawLayout);

        CameraVideoDeal();
    }

    public void setDrawerLayoutButtonClickColor() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawer_layout.setScrimColor(0x00ffffff);
        set_up_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (drawer_layout.isDrawerOpen(GravityCompat.END)) {
                    drawer_layout.closeDrawer(GravityCompat.END);
                } else {
                    drawer_layout.openDrawer(GravityCompat.END);
                }
            }
        });

        Abs_ctrl_default_button.setOnClickListener(v -> {

            if (Abs_ctrl_default_button_drawable != null) {

                Abs_ctrl_default_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_IN);
                Abs_ctrl_default_button.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));

                Abs_ctrl_drag_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                Abs_ctrl_drag_button.setTextColor(context.getResources().getColor(android.R.color.white));

                Rel_ctrl_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                Rel_ctrl_button.setTextColor(context.getResources().getColor(android.R.color.white));

                KeyMouse_state = true;
                keyMouseAbsCtrlState = false;
                CustomTouchListener.KeyMouse_state(KeyMouse_state, keyMouseAbsCtrlState);

            }
        });

        Abs_ctrl_drag_button.setOnClickListener(v -> {

            if (Abs_ctrl_drag_button_drawable != null) {

                Abs_ctrl_drag_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_IN);
                Abs_ctrl_drag_button.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));

                Abs_ctrl_default_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                Abs_ctrl_default_button.setTextColor(context.getResources().getColor(android.R.color.white));

                Rel_ctrl_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                Rel_ctrl_button.setTextColor(context.getResources().getColor(android.R.color.white));

                KeyMouse_state = true;
                keyMouseAbsCtrlState = true;
                CustomTouchListener.KeyMouse_state(KeyMouse_state, keyMouseAbsCtrlState);

            }
        });

        Rel_ctrl_button.setOnClickListener(v -> {

            if (Rel_ctrl_button_drawable != null) {
                System.out.println("in this rel button");
                Rel_ctrl_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_IN);
                Rel_ctrl_button.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));

                Abs_ctrl_drag_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                Abs_ctrl_drag_button.setTextColor(context.getResources().getColor(android.R.color.white));

                Abs_ctrl_default_button_drawable.setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                Abs_ctrl_default_button.setTextColor(context.getResources().getColor(android.R.color.white));

                KeyMouse_state = false;
                keyMouseAbsCtrlState = false;
                CustomTouchListener.KeyMouse_state(KeyMouse_state, keyMouseAbsCtrlState);
            }
        });
    }

    private void CameraVideoDeal(){
        @SuppressLint("NonConstantResourceId")
        View.OnClickListener buttonClickListener = view -> {
            switch (view.getId()) {
                case R.id.action_device:
                    activity.showDeviceListDialog();
                    break;
                case R.id.action_safely_eject:
                    activity.safelyEject();
                    break;
                case R.id.action_control:
                    activity.showCameraControlsDialog();
                    break;
                case R.id.action_video_format:
                    activity.showVideoFormatDialog();
                    break;
                case R.id.action_rotate_90_CW:
                    activity.rotateBy(90);
                    break;
                case R.id.action_rotate_90_CCW:
                    activity.rotateBy(-90);
                    break;
                case R.id.action_flip_horizontally:
                    activity.flipHorizontally();
                    break;
                case R.id.action_flip_vertically:
                    activity.flipVertically();
                    break;
                case R.id.ScreenHost_Picture:
                    activity.takePicture();
                    break;
                case R.id.Recording_Video:
                    activity.toggleVideoRecord(!mIsRecording);
                    break;
                case R.id.Close_DrawLayout:
                    if (drawer_layout.isDrawerOpen(GravityCompat.END)) {
                        drawer_layout.closeDrawer(GravityCompat.END);
                    }
                    break;
                default:
                    break;
            }
        };

        action_device.setOnClickListener(buttonClickListener);
        action_safely_eject.setOnClickListener(buttonClickListener);
        action_control.setOnClickListener(buttonClickListener);
        action_video_format.setOnClickListener(buttonClickListener);
        action_rotate_90_CW.setOnClickListener(buttonClickListener);
        action_rotate_90_CCW.setOnClickListener(buttonClickListener);
        action_flip_horizontally.setOnClickListener(buttonClickListener);
        action_flip_vertically.setOnClickListener(buttonClickListener);
        ScreenHost_Picture.setOnClickListener(buttonClickListener);
        Recording_Video.setOnClickListener(buttonClickListener);
        Close_DrawLayout.setOnClickListener(buttonClickListener);
    }
}