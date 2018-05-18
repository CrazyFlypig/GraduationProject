package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.tool.ProjectTool;

import javax.swing.*;

/**
 * 界面元素控制集合
 */
public interface IElements {
    JButton openSerialPort = (JButton) ProjectTool.getElementByName("openSerialPort");
    JButton jbtnScan = (JButton) ProjectTool.getElementByName("jbtnScan");
    JButton closeSerialPort = (JButton) ProjectTool.getElementByName("closeSerialPort");
    JButton BtnTestData = (JButton) ProjectTool.getElementByName("BtnTestData");
    JButton BtnControllOption = (JButton) ProjectTool.getElementByName("BtnControllOption");
    JButton jbtnReceiveDataRateClear = (JButton) ProjectTool.getElementByName("jbtnReceiveDataRateClear");
    JButton jbtnEverySecondErrorCodeClear = (JButton) ProjectTool.getElementByName("jbtnEverySecondErrorCodeClear");
    JButton jbtnErrorCodeRateClear = (JButton) ProjectTool.getElementByName("jbtnErrorCodeRateClear");
    JButton jbtnEverySecondErrorCodeRateClear = (JButton) ProjectTool.getElementByName("jbtnEverySecondErrorCodeRateClear");
    JButton BtnAllClear = (JButton) ProjectTool.getElementByName("BtnAllClear");
    JButton jbtnTestTimeClear = (JButton) ProjectTool.getElementByName("jbtnTestTimeClear");
    JComboBox<String> jcmbTestRateBox = (JComboBox<String>) ProjectTool.getElementByName("jcmbTestRateBox");
    JComboBox<String> jcmbLineEncoding = (JComboBox<String>) ProjectTool.getElementByName("jcmbLineEncoding");
    JComboBox<String> jcmbErrorCorrectingcodes = (JComboBox<String>) ProjectTool.getElementByName("jcmbErrorCorrectingcodes");
    JComboBox<String> jcmbTestData = (JComboBox<String>) ProjectTool.getElementByName("jcmbTestData");
    JComboBox<String> jcmbBaudRate = (JComboBox<String>) ProjectTool.getElementByName("jcmbBaudRate");
    JComboBox<String> jcmbChekBit = (JComboBox<String>) ProjectTool.getElementByName("jcmbChekBit");
    JComboBox<String> jcmbStopBit = (JComboBox<String>) ProjectTool.getElementByName("jcmbStopBit");
    JComboBox<String> jcmbSerialPort = (JComboBox<String>) ProjectTool.getElementByName("jcmbSerialPort");
    JFrame mainFrame = (JFrame) ProjectTool.getElementByName("mainFrame");
    JLabel systemTimeLabel = (JLabel) ProjectTool.getElementByName("systemTimeLabel");
    JLabel jlblReceiveDataRateText = (JLabel) ProjectTool.getElementByName("jlblReceiveDataRateText");
    JLabel jlblEverySecondErrorCodeText = (JLabel) ProjectTool.getElementByName("jlblEverySecondErrorCodeText");
    JLabel jlblErrorCodeRateText = (JLabel) ProjectTool.getElementByName("jlblErrorCodeRateText");
    JLabel jlblEverySecondErrorCodeRateText = (JLabel) ProjectTool.getElementByName("jlblEverySecondErrorCodeRateText");
    JLabel jlblTestTimeDayText = (JLabel) ProjectTool.getElementByName("jlblTestTimeDayText");
    JLabel jlblTestTimeHourText = (JLabel) ProjectTool.getElementByName("jlblTestTimeHourText");
    JLabel jlblTestTimeMinuteText = (JLabel) ProjectTool.getElementByName("jlblTestTimeMinuteText");
    JLabel jlblTestTimeSecondText = (JLabel) ProjectTool.getElementByName("jlblTestTimeSecondText");
    JLabel jlblErrorCodeRateTextX = (JLabel) ProjectTool.getElementByName("jlblErrorCodeRateTextX");
    JLabel jlblEverySecondErrorCodeRateTextX = (JLabel) ProjectTool.getElementByName("jlblEverySecondErrorCodeRateTextX");
    JLabel jlblAllErrorCodeText = (JLabel) ProjectTool.getElementByName("jlblAllErrorCodeText");
}
