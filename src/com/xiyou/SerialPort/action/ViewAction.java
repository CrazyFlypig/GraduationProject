package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.listener.PollingListener;
import com.xiyou.SerialPort.tool.FileOperator;
import com.xiyou.SerialPort.tool.SerialPortTool;
import com.xiyou.SerialPort.tool.TimeTool;
import gnu.io.SerialPort;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author cc
 */

public class ViewAction implements IElements {
    private static TimeTool timeTool = new TimeTool();

    /**
     * 开始时钟
     */
    public static void startTime() {
        timeTool = new TimeTool();
        timeTool.setTimeLabel(systemTimeLabel);
        timeTool.startTime();
    }

    /**
     * 退出程序
     */
    public static void exit() {
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                SerialPort serialPort = OpenSerialPortAction.getSerialPort();
                PollingListener listener = OpenSerialPortAction.getListener();
                timeTool.stopTime();//关闭时间线程
                SerialPortTool.closePort(serialPort);//关闭串口
                FileOperator.closeResource();//关闭文件流资源
                if(listener != null) {
                    listener.stopListening();//关闭轮训监听器
                }
                System.exit(0);//系统退出
            }
        });
    }
}
