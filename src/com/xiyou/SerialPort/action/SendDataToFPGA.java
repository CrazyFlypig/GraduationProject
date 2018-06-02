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
import java.util.ArrayList;
import java.util.List;

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
                if (serialPort == null) {
                    ProjectTool.showErrorMsg(mainFrame, "串口尚未打开！");
                    return;
                }

                /************************获取到控制编码*********************************/
                List<String> strList = new ArrayList<>();
                String rateCode = "0001" + ParamMap.getRateCode(jcmbTestRateBox.getSelectedItem().toString());
                strList.add(rateCode);
                String lineEncodingCode = "0010" + ParamMap.getEncodingCode(jcmbLineEncoding.getSelectedItem().toString());
                strList.add(lineEncodingCode);
                String errorCorrectCode = "0011" + ParamMap.getErrCorrectCodingCode(jcmbErrorCorrectingcodes.getSelectedItem().toString());
                strList.add(errorCorrectCode);
                String testDataCode = "0100" + ParamMap.getTestDataCode(jcmbTestData.getSelectedItem().toString());
                strList.add(testDataCode);
                /************************获取到控制编码*********************************/

                try {
                    for (String command : strList) {
                        int res = ProjectTool.stringToInt(command);
                        //发送数据
                        SerialPortTool.sendToPort(serialPort, res);
                        //记录发送数据
                        FileOperator.writeToTxFile(Integer.toHexString(res));
                    }
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
     * 测试是否联通
     */
    private static int code = 0;//测试模块的编码

    public static void test() {
        BtnTestData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SerialPort serialPort = OpenSerialPortAction.getSerialPort();
                if (serialPort == null) {
                    ProjectTool.showErrorMsg(mainFrame, "串口尚未打开！");
                    return;
                }

                if (code > 255) {
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
