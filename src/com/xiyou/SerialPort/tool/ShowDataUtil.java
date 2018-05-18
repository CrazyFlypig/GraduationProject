package com.xiyou.SerialPort.tool;

/**
 * @author cc
 */

public class ShowDataUtil {
    /**
     * 将接受的int类型数据转换为满8位的二进制字符串
     * @param data
     * @return
     */
    public static String packageBinString(int data) {
        String dataString = Integer.toBinaryString(data);
        int dataLen = dataString.length();

        StringBuilder sb = new StringBuilder();

        if(dataLen < 8) { //如果编码不够8位，凑成8位显示
            int subLen = 8 - dataLen;
            for(int i = 0; i < subLen; i++) {
                sb.append("0");
            }
        }

        sb.append(dataString);

        return sb.toString();
    }

    /**
     * 清空字符数组
     * @param arr
     * @param index
     */
    public static void clearArray(char[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = ' ';
        }
    }

    /**
     * 初始化字符数组为全零
     */
    public static void initArray(char[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = '0';
        }
    }
}
