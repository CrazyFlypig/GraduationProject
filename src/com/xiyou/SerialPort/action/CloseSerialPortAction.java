package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.tool.SerialPortTool;
import gnu.io.SerialPort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author cc
 */

public class CloseSerialPortAction implements IElements {
    /**
     * 关闭串口
     */
    public static void close() {
        closeSerialPort.setEnabled(false);
        closeSerialPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SerialPort serialPort = OpenSerialPortAction.getSerialPort();
                SerialPortTool.closePort(serialPort);
                OpenSerialPortAction.listener.stopListening();
                OpenSerialPortAction.setSerialPort(null);
                jbtnScan.setEnabled(true);
                openSerialPort.setEnabled(true);
                closeSerialPort.setEnabled(false);
            }
        });
    }
}
