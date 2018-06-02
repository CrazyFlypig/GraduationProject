package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.exception.NotSerialPortException;
import com.xiyou.SerialPort.listener.PollingListener;
import com.xiyou.SerialPort.tool.ProjectTool;
import com.xiyou.SerialPort.tool.SerialPortTool;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 开启串口类
 *
 * @author cc
 */

public class OpenSerialPortAction implements IElements {
    private static int baudRate = 9600;//默认波特率
    private static int checkBit = SerialPort.PARITY_NONE;//默认检测位
    private static int stopBit = SerialPort.STOPBITS_1;//默认停止位

    private static SerialPort serialPort = null;//打开的串口

    public static PollingListener listener = null;//轮训监听器

    public static void setSerialPort(SerialPort serialPort) {
        OpenSerialPortAction.serialPort = serialPort;
    }

    public static SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     * 打开串口
     */
    public static void open() {
        openSerialPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String com = (String) jcmbSerialPort.getSelectedItem();
                if (com == null) {
                    ProjectTool.showErrorMsg(mainFrame, "未找到可用的串口！");
                    return;
                }

                baudRate = Integer.valueOf(jcmbBaudRate.getSelectedItem().toString());
                checkBit = ProjectTool.getSerialPortParameteByName(jcmbChekBit.getSelectedItem().toString());
                stopBit = ProjectTool.getSerialPortParameteByName(jcmbStopBit.getSelectedItem().toString());

                try {
                    serialPort = SerialPortTool.openPort(com, baudRate, stopBit, checkBit);
                    jbtnScan.setEnabled(false);//扫描按钮不可用
                    openSerialPort.setEnabled(false);//打开串口按钮不可用
                    closeSerialPort.setEnabled(true);//关闭串口按钮可用

                    //轮询模式 扫描串口中是否有数据来
                    listener = new PollingListener(serialPort);
                    Thread t = new Thread(listener);
                    t.start();
                } catch (NoSuchPortException e) {
                    ProjectTool.showErrorMsg(mainFrame, "没有【" + com + "】这样的串口！");
                } catch (PortInUseException e) {
                    ProjectTool.showErrorMsg(mainFrame, "【" + com + "】已被占用！");
                } catch (UnsupportedCommOperationException e) {
                    ProjectTool.showErrorMsg(mainFrame, "【" + com + "】不支持！");
                } catch (NotSerialPortException e) {
                    ProjectTool.showErrorMsg(mainFrame, "【" + com + "】不是串口！");
                }
            }
        });
    }

    public static PollingListener getListener() {
        return listener;
    }
}
