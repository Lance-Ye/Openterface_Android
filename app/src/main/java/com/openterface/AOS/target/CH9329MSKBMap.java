/**
* @Title: CH9329MSKBMap
* @Package com.openterface.AOS.target
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
package com.openterface.AOS.target;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CH9329MSKBMap {

    public static Set<String> HashSet() {
        Set<String> keyCodesToCheck = new HashSet<>();
        keyCodesToCheck.add("E0");
        keyCodesToCheck.add("E1");
        keyCodesToCheck.add("E2");
        keyCodesToCheck.add("E4");
        keyCodesToCheck.add("E5");
        keyCodesToCheck.add("E6");
        return keyCodesToCheck;
    }

    public static Map<Object, String> CmdData() {
        Map<Object, String> CmdData = new HashMap<>();
        CmdData.put("CmdKB_HID", "02");
        CmdData.put("CmdMS_ABS", "04");
        CmdData.put("CmdMS_REL", "05");

        return CmdData;
    }

    public static Map<Object, String> DataLen() {
        Map<Object, String> DataLen = new HashMap<>();
        DataLen.put("DataLenRelMS", "05");
        DataLen.put("DataLenAbsMS", "07");
        DataLen.put("DataLenKB", "08");

        return DataLen;
    }

    public static Map<Object, String> KBShortCutKey() {
        Map<Object, String> DataLen = new HashMap<>();
        DataLen.put("ShortCutKeyCtrlNull", "00");
        DataLen.put("ShortCutKeyShiftNull", "00");
        DataLen.put("ShortCutKeyAltNull", "00");
        DataLen.put("ShortCutKeyWinNull", "00");
        DataLen.put("Ctrl", "01");
        DataLen.put("Shift", "02");
        DataLen.put("Alt", "04");
        DataLen.put("Win", "08");

        return DataLen;
    }

    public static Map<Object, String> KBFunctionKey() {
        Map<Object, String> DataLen = new HashMap<>();
        DataLen.put("FunctionKey", "05");

        return DataLen;
    }

    public static Map<Object, String> MSAbsData() {
        Map<Object, String> MSAbsData = new HashMap<>();
        //first key must is 0x02
        MSAbsData.put("FirstData", "02");

        //second key have 3 options,lef,middle, right key
        MSAbsData.put("SecNullData", "00");
        MSAbsData.put("SecLeftData", "01");
        MSAbsData.put("SecRightData", "02");
        MSAbsData.put("SecMiddleData", "04");

        //three and four is x,five and six is y

        //seven is Scroll Wheel
        MSAbsData.put("SlideUp", "02");
        MSAbsData.put("Downward", "82");

        return MSAbsData;
    }

    public static Map<Object, String> MSRelData() {
        Map<Object, String> MSRelData = new HashMap<>();
        //first rel key must is 0x01
        MSRelData.put("FirstData", "01");

        //second key have 3 options,lef,middle, right key
        MSRelData.put("SecNullData", "00");
        MSRelData.put("SecLeftData", "01");
        MSRelData.put("SecRightData", "02");
        MSRelData.put("SecMiddleData", "04");

        //three and four is x,five and six is y

        //seven is Scroll Wheel
        MSRelData.put("SlideUp", "02");
        MSRelData.put("Downward", "82");

        MSRelData.put("LeftRel", "FD");
        MSRelData.put("RightRel", "03");
        MSRelData.put("UpRel", "FD");
        MSRelData.put("DownRel", "03");

        return MSRelData;
    }

    public static Map<Object, String> DataNull() {
        Map<Object, String> DataNull = new HashMap<>();
        DataNull.put("DataNull", "00");
        DataNull.put("DataTest", "10");
        return DataNull;
    }

    public static Map<Object, String> getKeyCodeMap() {
        Map<Object, String> keyCodeMap = new HashMap<>();
        keyCodeMap.put("prefix1", "57");
        keyCodeMap.put("prefix2", "AB");
        keyCodeMap.put("address", "00");

        //click key
        keyCodeMap.put("A", "04");
        keyCodeMap.put("B", "05");
        keyCodeMap.put("C", "06");
        keyCodeMap.put("D", "07");
        keyCodeMap.put("E", "08");
        keyCodeMap.put("F", "09");
        keyCodeMap.put("G", "0A");
        keyCodeMap.put("H", "0B");
        keyCodeMap.put("I", "0C");
        keyCodeMap.put("J", "0D");
        keyCodeMap.put("K", "0E");
        keyCodeMap.put("L", "0F");
        keyCodeMap.put("M", "10");
        keyCodeMap.put("N", "11");
        keyCodeMap.put("O", "12");
        keyCodeMap.put("P", "13");
        keyCodeMap.put("Q", "14");
        keyCodeMap.put("R", "15");
        keyCodeMap.put("S", "16");
        keyCodeMap.put("T", "17");
        keyCodeMap.put("U", "18");
        keyCodeMap.put("V", "19");
        keyCodeMap.put("W", "1A");
        keyCodeMap.put("X", "1B");
        keyCodeMap.put("Y", "1C");
        keyCodeMap.put("Z", "1D");
        keyCodeMap.put("a", "04");
        keyCodeMap.put("b", "05");
        keyCodeMap.put("c", "06");
        keyCodeMap.put("d", "07");
        keyCodeMap.put("e", "08");
        keyCodeMap.put("f", "09");
        keyCodeMap.put("g", "0A");
        keyCodeMap.put("h", "0B");
        keyCodeMap.put("i", "0C");
        keyCodeMap.put("j", "0D");
        keyCodeMap.put("k", "0E");
        keyCodeMap.put("l", "0F");
        keyCodeMap.put("m", "10");
        keyCodeMap.put("n", "11");
        keyCodeMap.put("o", "12");
        keyCodeMap.put("p", "13");
        keyCodeMap.put("q", "14");
        keyCodeMap.put("r", "15");
        keyCodeMap.put("s", "16");
        keyCodeMap.put("t", "17");
        keyCodeMap.put("u", "18");
        keyCodeMap.put("v", "19");
        keyCodeMap.put("w", "1A");
        keyCodeMap.put("x", "1B");
        keyCodeMap.put("y", "1C");
        keyCodeMap.put("z", "1D");

        keyCodeMap.put("1", "1E");keyCodeMap.put("!", "1E");
        keyCodeMap.put("2", "1F");keyCodeMap.put("@", "1F");
        keyCodeMap.put("3", "20");keyCodeMap.put("#", "20");
        keyCodeMap.put("4", "21");keyCodeMap.put("$", "21");
        keyCodeMap.put("5", "22");keyCodeMap.put("%", "22");
        keyCodeMap.put("6", "23");keyCodeMap.put("^", "23");
        keyCodeMap.put("7", "24");keyCodeMap.put("&", "24");
        keyCodeMap.put("8", "25");keyCodeMap.put("*", "25");
        keyCodeMap.put("9", "26");keyCodeMap.put("(", "26");
        keyCodeMap.put("0", "27");keyCodeMap.put(")", "27");
        keyCodeMap.put("`", "35");keyCodeMap.put("~", "35");
        keyCodeMap.put("-", "2D");keyCodeMap.put("_", "2D");
        keyCodeMap.put("+", "2E");keyCodeMap.put("=", "2E");
        keyCodeMap.put("[", "2F");keyCodeMap.put("{", "2F");
        keyCodeMap.put("]", "30");keyCodeMap.put("}", "30");
        keyCodeMap.put("|", "64");keyCodeMap.put("\\", "64");
        keyCodeMap.put(";", "33");keyCodeMap.put(":", "33");
        keyCodeMap.put("'", "34");keyCodeMap.put("\"", "34");
        keyCodeMap.put(",", "36");keyCodeMap.put("<", "36");
        keyCodeMap.put(".", "37");keyCodeMap.put(">", "37");
        keyCodeMap.put("/", "38");keyCodeMap.put("?", "38");

        //function key
        keyCodeMap.put("GRAVE", "35");
        keyCodeMap.put("MINUS", "2D");
        keyCodeMap.put("EQUALS", "2E");
        keyCodeMap.put("LEFT_BRACKET", "2F");
        keyCodeMap.put("RIGHT_BRACKET", "30");
        keyCodeMap.put("BACKSLASH", "31");
        keyCodeMap.put("SEMICOLON", "33");
        keyCodeMap.put("APOSTROPHE", "34");
        keyCodeMap.put("COMMA", "36");
        keyCodeMap.put("PERIOD", "37");
        keyCodeMap.put("SLASH", "38");
        keyCodeMap.put("CapsLk", "39");
        keyCodeMap.put("CAPS_LOCK", "39");

        keyCodeMap.put("F1", "3A");
        keyCodeMap.put("F2", "3B");
        keyCodeMap.put("F3", "3C");
        keyCodeMap.put("F4", "3D");
        keyCodeMap.put("F5", "3E");
        keyCodeMap.put("F6", "3F");
        keyCodeMap.put("F7", "40");
        keyCodeMap.put("F8", "41");
        keyCodeMap.put("F9", "42");
        keyCodeMap.put("F10", "43");
        keyCodeMap.put("F11", "44");
        keyCodeMap.put("F12", "45");
        keyCodeMap.put("SYSRQ", "46");
        keyCodeMap.put("SCROLL_LOCK", "47");
        keyCodeMap.put("BREAK", "48");
        keyCodeMap.put("INSERT", "49");
        keyCodeMap.put("FORWARD_DEL", "4C");
        keyCodeMap.put("MOVE_HOME", "4A");
        keyCodeMap.put("PAGE_UP", "4B");
        keyCodeMap.put("Delete", "4C");
        keyCodeMap.put("MOVE_END", "4D");
        keyCodeMap.put("PAGE_DOWN", "4E");

        keyCodeMap.put("CTRL_LEFT", "E0");
        keyCodeMap.put("SHIFT_LEFT", "E1");
        keyCodeMap.put("ALT_LEFT", "E2");
        keyCodeMap.put("Win", "E3");
        keyCodeMap.put("CTRL_RIGHT", "E4");
        keyCodeMap.put("SHIFT_RIGHT", "E5");
        keyCodeMap.put("ALT_RIGHT", "E6");
        keyCodeMap.put("TAB", "2B");
        keyCodeMap.put("SPACE", "2C");
        keyCodeMap.put("BACK", "29");
        keyCodeMap.put("ENTER", "28");
        keyCodeMap.put("DEL", "2A");
        keyCodeMap.put("Esc", "29");
        keyCodeMap.put("PrtSc", "46");
        keyCodeMap.put("ScrLk", "47");
        keyCodeMap.put("Pause", "48");
        keyCodeMap.put("Ins", "49");
        keyCodeMap.put("Home", "4A");
        keyCodeMap.put("End", "4D");
        keyCodeMap.put("PgUp", "4B");
        keyCodeMap.put("PgDn", "4E");
        keyCodeMap.put("NumLk", "53");

        keyCodeMap.put("DPAD_LEFT", "50");
        keyCodeMap.put("DPAD_RIGHT", "4F");
        keyCodeMap.put("DPAD_DOWN", "51");
        keyCodeMap.put("DPAD_UP", "52");

        keyCodeMap.put("NUM_LOCK", "53");
        keyCodeMap.put("NUMPAD_DIVIDE", "54");
        keyCodeMap.put("NUMPAD_MULTIPLY", "55");
        keyCodeMap.put("NUMPAD_SUBTRACT", "56");
        keyCodeMap.put("NUMPAD_ADD", "57");

        //release keyboard data
        keyCodeMap.put("release", "57AB00020800000000000000000C");
        keyCodeMap.put("releaseRel", "57AB0005050100000000");

        return keyCodeMap;
    }

}
