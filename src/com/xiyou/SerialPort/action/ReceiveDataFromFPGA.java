package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.exception.ReadDataFromSerialPortFailureException;
import com.xiyou.SerialPort.exception.SerialPortInputStreamCloseFailureException;
import com.xiyou.SerialPort.tool.FileOperator;
import com.xiyou.SerialPort.tool.ProjectTool;
import com.xiyou.SerialPort.tool.SerialPortTool;
import com.xiyou.SerialPort.tool.ShowDataUtil;
import gnu.io.SerialPort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 */

public class ReceiveDataFromFPGA implements IElements {
    /**
     * 接收来自开发板的数据
     */
    public static void receiveData() {
        SerialPort serialPort = OpenSerialPortAction.getSerialPort();
        List<Integer> dataList = new ArrayList<>();

        if (serialPort == null) {
            ProjectTool.showErrorMsg(null, "串口对象为空！监听失败！");
            return;
        } else {
            try {
                int data;
                data = SerialPortTool.readFromPort(serialPort);
                dataList.add(data);
                if (dataList.size() == 2) {
                    FileOperator.writeToRxFile(Integer.toHexString(data));//向文件中写入16数据
                    ShowResultAction.showData(ShowDataUtil.packageBinString(data));//接收数据显示
                }
            } catch (SerialPortInputStreamCloseFailureException e) {
                e.printStackTrace();
            } catch (ReadDataFromSerialPortFailureException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
