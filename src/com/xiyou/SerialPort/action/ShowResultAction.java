package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.tool.ProjectTool;
import com.xiyou.SerialPort.tool.ShowDataUtil;

/**
 * @author cc
 */

public class ShowResultAction implements IElements {
    private static char[] showString_000 = new char[8];//存储字符  分为四份
    private static char[] showString_001 = new char[8];
    private static char[] showString_010 = new char[8];
    private static char[] showString_011 = new char[8];
    private static char[] showString_100 = new char[8];

    private static int index_000 = 7;//下标
    private static int index_001 = 7;
    private static int index_010 = 7;
    private static int index_011 = 7;
    private static int index_100 = 7;

    static {//初次使用数组先进行清空操作
        ShowDataUtil.initArray(showString_000);
        ShowDataUtil.initArray(showString_001);
        ShowDataUtil.initArray(showString_010);
        ShowDataUtil.initArray(showString_011);
        ShowDataUtil.initArray(showString_100);
    }

    /**
     * 是否将指针归零
     * 根据接受到的data字符串的第三位XXX X XXXX数据
     * 0：表示显示数字的位置指针归零，从最右边开始
     * @param data 操作指针的标志位
     */
    private static void controlIndex(String locationString, String data) {
        if("0000".equals(locationString + data)) {
            index_000 = 7;
        } else if("0010".equals(locationString + data)) {
            index_001 = 7;
        } else if("0100".equals(locationString + data)) {
            index_010 = 7;
        } else if("0110".equals(locationString + data)) {
            index_011 = 7;
        } else if("1000".equals(locationString + data)) {
            index_100 = 7;
        }
    }

    /**
     * 控制将数据显示在哪行
     * @param locationString
     */
    private static void showWhichLineInfo(String locationString, char numCh) {
        if(locationString.equals("000")) {
            if(index_000 < 0) {
                index_000 = 0;
            }
            showString_000[index_000--] = numCh;
            String showStr = new String(showString_000);
            jlblTestTimeSecondText.setText(showStr.substring(6, 6+2));
            jlblTestTimeMinuteText.setText(showStr.substring(4, 4+2));
            jlblTestTimeHourText.setText(showStr.substring(2, 2+2));
            jlblTestTimeDayText.setText(showStr.substring(0, 0+2));
        } else if(locationString.equals("001")) {
            if(index_001 < 0) {
                index_001 = 0;
            }
            showString_001[index_001--] = numCh;
            String showStr = new String(showString_001);//0 0000003
            String str_high1 = showStr.substring(0, 1);
            String str_low7 = showStr.substring(1, 1+7);
            jlblReceiveDataRateText.setText(str_high1 + "." + str_low7);
        } else if(locationString.equals("010")) {
            if(index_010 < 0) {
                index_010 = 0;
            }
            showString_010[index_010--] = numCh;
            String showStr = new String(showString_010);
            String str_high1 = showStr.substring(0, 1);
            String str_low6 = showStr.substring(1, 1+6);
            String str_last = showStr.substring(7, 7+1);
            jlblErrorCodeRateText.setText(str_high1 + "." + str_low6);
            jlblErrorCodeRateTextX.setText(str_last);
        } else if(locationString.equals("011")) {
            if(index_011 < 0) {
                index_011 = 0;
            }
            showString_011[index_011--] = numCh;
            String showStr = new String(showString_011);
            jlblEverySecondErrorCodeText.setText(showStr);
        } else if(locationString.equals("100")) {
            if(index_100 < 0) {
                index_100 = 0;
            }
            showString_100[index_100--] = numCh;
            String showStr = new String(showString_100);
            String str_high1 = showStr.substring(0, 1);
            String str_low6 = showStr.substring(1, 1+6);
            String str_last = showStr.substring(7, 7+1);
            jlblEverySecondErrorCodeRateText.setText(str_high1 + "." + str_low6);
            jlblEverySecondErrorCodeRateTextX.setText(str_last);
        }
    }

    /**
     * 显示结果
     * @param data
     * @throws Exception
     */

    public static void showData(String data) throws Exception {
        String locationString = data.substring(0, 3);//前三位表示显示的位置
        String isClearFlag = data.substring(3, 4);//第四位数据标志位
        int num = ProjectTool.stringToInt(data.substring(4));//将BCD码转换为十进制的数据
        char[] numChArr = String.valueOf(num).toCharArray();

        char numCh = 'X';//要显示的字符 默认为X，要是显示的是X，就说明接收到了错误的数据
        if(numChArr.length == 1) {//表明接收的BCD码的数据在0~9之间，即表示0~9的数值
            numCh = numChArr[0];
        } else if(numChArr[0] == '1' && numChArr[1] == '5') {//1111为15 代表空格
            numCh = ' ';
        }

        controlIndex(locationString, isClearFlag);//控制第四位  要是第四位为0 调节指针到最后边
        showWhichLineInfo(locationString, numCh);//控制BCD码的显示位置
    }

    public static char[] getShowString_000() {
        return showString_000;
    }

    public static void setShowString_000(char[] showString_000) {
        ShowResultAction.showString_000 = showString_000;
    }

    public static char[] getShowString_001() {
        return showString_001;
    }

    public static void setShowString_001(char[] showString_001) {
        ShowResultAction.showString_001 = showString_001;
    }

    public static char[] getShowString_010() {
        return showString_010;
    }

    public static void setShowString_010(char[] showString_010) {
        ShowResultAction.showString_010 = showString_010;
    }

    public static char[] getShowString_011() {
        return showString_011;
    }

    public static void setShowString_011(char[] showString_011) {
        ShowResultAction.showString_011 = showString_011;
    }

    public static char[] getShowString_100() {
        return showString_100;
    }

    public static void setShowString_100(char[] showString_100) {
        ShowResultAction.showString_100 = showString_100;
    }

    public static int getIndex_000() {
        return index_000;
    }

    public static void setIndex_000(int index_000) {
        ShowResultAction.index_000 = index_000;
    }

    public static int getIndex_001() {
        return index_001;
    }

    public static void setIndex_001(int index_001) {
        ShowResultAction.index_001 = index_001;
    }

    public static int getIndex_010() {
        return index_010;
    }

    public static void setIndex_010(int index_010) {
        ShowResultAction.index_010 = index_010;
    }

    public static int getIndex_011() {
        return index_011;
    }

    public static void setIndex_011(int index_011) {
        ShowResultAction.index_011 = index_011;
    }

    public static int getIndex_100() {
        return index_100;
    }

    public static void setIndex_100(int index_100) {
        ShowResultAction.index_100 = index_100;
    }
}
