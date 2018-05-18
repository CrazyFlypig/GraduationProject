package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.tool.ProjectTool;
import com.xiyou.SerialPort.tool.SerialPortTool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author cc
 */

public class ScanSerialPortAction implements IElements {
    public static void scan() {
        jbtnScan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jcmbSerialPort.removeAllItems();//扫描时先移除所有的串口内容
                ArrayList<String> portList = SerialPortTool.findPort();
                if(portList.isEmpty()) {
                    ProjectTool.showErrorMsg(mainFrame, "未检索到串口！请检查设备是否工作正常！");
                    return;
                }

                for(int i = 0; i < portList.size(); i++) {
                    jcmbSerialPort.addItem(portList.get(i));
                }
            }
        });
    }
}
