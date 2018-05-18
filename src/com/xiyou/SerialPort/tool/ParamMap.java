package com.xiyou.SerialPort.tool;

import com.xiyou.SerialPort.exception.CodeHasAlreadyUserException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cc
 */

public class ParamMap {
    private static final Map<String, String> rateMap = new HashMap<String, String>();//存放键值对
    private static ArrayList<String> rateList = new ArrayList<String>();//存放名称

    private static final Map<String, String> encodingMap = new HashMap<String, String>();
    private static ArrayList<String> encodingList = new ArrayList<String>();

    private static final Map<String, String> errCorrectCodingMap =  new HashMap<String, String>();
    private static final Map<String, String> errCorrectCodingMapBake =  new HashMap<String, String>();//区别相同的键值
    private static ArrayList<String> errCorrectCodingList = new ArrayList<String>();

    private static final Map<String, String> testDataMap =  new HashMap<String, String>();
    private static ArrayList<String> testDataList = new ArrayList<String>();

    private static ArrayList<String> baudRateList = new ArrayList<String>();

    public static void setRateMap(String code, String rate) throws Exception {
        if(rateMap.get(code) != null) {
            throw new CodeHasAlreadyUserException("【" + rate + "】所对应的"+"编号【" + code + "】已经被使用！");
        }
        rateMap.put(code, rate);
        rateList.add(rate);
    }

    public static void setEncodingMap(String code, String encoding) throws Exception {
        if(encodingMap.get(code) != null) {
            throw new CodeHasAlreadyUserException("【" + encoding + "】所对应的"+"编号【" + code + "】已经被使用！");
        }
        encodingMap.put(code, encoding);
        encodingList.add(encoding);
    }

    public static void setErrCorrectCodingMap(String code, String errCorrectCoding) throws Exception {
        if(!errCorrectCodingMap.containsKey(code)) {
            errCorrectCodingMap.put(code, errCorrectCoding);
        } else {
            errCorrectCodingMapBake.put(code, errCorrectCoding);
        }
        errCorrectCodingList.add(errCorrectCoding);
    }

    public static void setTestDataMap(String code, String testData) throws Exception {
        if(testDataMap.get(code) != null) {
            throw new CodeHasAlreadyUserException("【" + testData + "】所对应的"+"编号【" + code + "】已经被使用！");
        }
        testDataMap.put(code, testData);
        testDataList.add(testData);
    }

    public static void setBaudRate(String baudRate) {
        if(!baudRateList.contains(baudRate)) {
            baudRateList.add(baudRate);
        }
    }

    public static String getRateCode(String value) {
        for(String key : rateMap.keySet()) {
            if(rateMap.get(key).equals(value)) {
                return key;
            }
        }

        return null;
    }

    public static ArrayList<String> getRateList() {
        return rateList;
    }

    public static String getEncodingCode(String value) {
        for(String key : encodingMap.keySet()) {
            if(encodingMap.get(key).equals(value)) {
                return key;
            }
        }

        return null;
    }

    public static ArrayList<String> getEncodingList() {
        return encodingList;
    }

    public static String getErrCorrectCodingCode(String value) {
        for(String key : errCorrectCodingMap.keySet()) {
            if(errCorrectCodingMap.get(key).equals(value)) {
                return key;
            }
        }

        for(String key : errCorrectCodingMapBake.keySet()) {
            if(errCorrectCodingMapBake.get(key).equals(value)) {
                return key;
            }
        }

        return null;
    }

    public static ArrayList<String> getErrCorrectCodingList() {
        return errCorrectCodingList;
    }

    public static String getTestDataCode(String value) {
        for(String key : testDataMap.keySet()) {
            if(testDataMap.get(key).equals(value)) {
                return key;
            }
        }

        return null;
    }

    public static ArrayList<String> getTestDataList() {
        return testDataList;
    }

    public static ArrayList<String> getBaudRateList() {
        return baudRateList;
    }
}
