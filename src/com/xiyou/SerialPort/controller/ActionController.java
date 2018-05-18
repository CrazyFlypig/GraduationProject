package com.xiyou.SerialPort.controller;

import com.xiyou.SerialPort.action.*;

/**
 * @author cc
 */

public class ActionController {
    /**
     * 开启监听事件
     */
    public static void startActionListening() {
        //扫描串口列表
        ScanSerialPortAction.scan();

        //打开串口监听事件
        OpenSerialPortAction.open();

        //关闭串口监听事件
        CloseSerialPortAction.close();

        //测试按钮
        SendDataToFPGA.test();

        //点击设置按钮，向开发板发送编码
        SendDataToFPGA.send();

        //全部清空
        ClearAction.allClear();
        //单行清空
        ClearAction.singleLineClear();
        //界面系统时间
        ViewAction.startTime();

        //界面退出
        ViewAction.exit();
    }
}
