package com.xiyou.SerialPort.tool;

import com.xiyou.SerialPort.action.ShowResultAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cc
 */

public class ShowDataUtil {
    public static String param = new String();
    public static char[] dataChars = new char[48];
    public static Map<String, String> params = new HashMap<>();

    /**
     * 将接受的int类型数据转换为满8位的二进制字符串
     *
     * @param data
     * @return str
     */
    public static String packageBinString(int data) {
        String dataString = Integer.toBinaryString(data);
        int dataLen = dataString.length();
        StringBuilder sb = new StringBuilder();
        if (dataLen < 8) { //如果编码不够8位，凑成8位显示
            int subLen = 8 - dataLen;
            for (int i = 0; i < subLen; i++) {
                sb.append("0");
            }
        }
        sb.append(dataString);
        return sb.toString();
    }

    /**
     * 初始化字符数组为全零
     */
    public static void initArray(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = '0';
        }
    }

    /**
     * 构造数据
     */
    public static void createParamValue(List<Integer> dataList) throws InterruptedException {
        System.out.println("构造数据");
        if (dataList == null) {
            System.out.println("param: " + param + " data:" + new String(dataChars));
            if (!param.equals("")) {
                params.put(param, new String(dataChars));
            }
            initArray(dataChars);//初始化数据
            param = "";
            System.out.println("params.size: " + params.size());
            if (params.size() == 5) {
                System.out.println("执行展示");
                ShowResultAction.getResult(params);

            }
            return;
        }
        String value1 = packageBinString(dataList.get(0));
        String value2 = packageBinString(dataList.get(1));
        char[] data = null;
        //取出参数和位置
        if (value2.startsWith("00")) {
            String tmp = value1;
            value1 = value2;
            value2 = tmp;
        }
        System.out.println(value1 + "--" + value2);
        Thread.sleep(1000);
        String newParam = value1.substring(2, 5);
        if (param.equals("")) {
            param = newParam;
            initArray(dataChars);
        }
        //判断是新的参数值，则先执行显示，再添入新值，否则继续填入
        if (param.equals(newParam) == false) {
            System.out.println("param: " + param + " data:" + new String(dataChars));
            params.put(param, new String(dataChars));
            System.out.println("params.size: " + params.size());
            //添入值；
            initArray(dataChars);//初始化数据
            param = newParam;
        }
        if (param.equals(newParam)) {
            int index = ProjectTool.stringToInt(value1.substring(6, 8));
            System.out.println("参数：" + newParam + " 位置：" + index);
            int start = index * 5 + index;
            if (value2.startsWith("11")) {
                data = value2.substring(2, 8).toCharArray();
            } else throw null;
            for (int i = 5; i > 0; i--) {
                dataChars[47 - (start + i)] = data[5 - i];
            }
        }
        System.out.println(dataChars.length);
        for (char c : dataChars) {
            System.out.printf(c + "");

        }
        System.out.println();
    }
}
