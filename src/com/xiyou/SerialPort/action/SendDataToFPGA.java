package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.exception.SendDataToSerialPortFailureException;
import com.xiyou.SerialPort.tool.FileOperator;
import com.xiyou.SerialPort.tool.ParamMap;
import com.xiyou.SerialPort.tool.ProjectTool;
import com.xiyou.SerialPort.tool.SerialPortTool;
import gnu.io.SerialPort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author cc
 */

public class SendDataToFPGA implements IElements {
    /**
     * 给开发板发送指令
     */
    public static void send() {
        BtnControllOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SerialPort serialPort = OpenSerialPortAction.getSerialPort();
                if(serialPort == null) {
                    ProjectTool.showErrorMsg(mainFrame, "串口尚未打开！");
                    return;
                }

                /************************获取到控制编码*********************************/
                StringBuilder str = new StringBuilder();
                String rateCode = ParamMap.getRateCode(jcmbTestRateBox.getSelectedItem().toString());
                String lineEncodingCode = ParamMap.getEncodingCode(jcmbLineEncoding.getSelectedItem().toString());
                String errorCorrectCode = ParamMap.getErrCorrectCodingCode(jcmbErrorCorrectingcodes.getSelectedItem().toString());
                String testDataCode = ParamMap.getTestDataCode(jcmbTestData.getSelectedItem().toString());
                str.append(rateCode).append(lineEncodingCode).append(errorCorrectCode).append(testDataCode);
                int res = ProjectTool.stringToInt(str.toString());
                /************************获取到控制编码*********************************/

                try {
                    SerialPortTool.sendToPort(serialPort, res);
                    //记录发送的数据
                    FileOperator.writeToTxFile(Integer.toHexString(res));
                } catch (SendDataToSerialPortFailureException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 指令测试（给开发板发送）
     */
    private static int code = 0;//测试模块的编码
    public static void test() {
        BtnTestData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SerialPort serialPort = OpenSerialPortAction.getSerialPort();
                if(serialPort == null) {
                    ProjectTool.showErrorMsg(mainFrame, "串口尚未打开！");
                    return;
                }

                if(code > 255) {
                    code = 0;
                }

                try {
                    SerialPortTool.sendToPort(serialPort, code);
                    //记录发送的数据
                    FileOperator.writeToTxFile(Integer.toHexString(code));
                    code++;
                } catch (SendDataToSerialPortFailureException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
