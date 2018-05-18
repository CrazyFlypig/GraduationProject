package com.xiyou.SerialPort.listener;

import com.xiyou.SerialPort.action.ShowResultAction;
import com.xiyou.SerialPort.tool.FileOperator;
import com.xiyou.SerialPort.tool.ShowDataUtil;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cc
 */

public class PollingListener implements Runnable {
    private SerialPort serialPort = null;
    private boolean goon = true;
    private InputStream is = null;

    public PollingListener() {
    }

    public PollingListener(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void SetSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }



    public void stopListening() {
        goon = false;
        try {
            if(is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            is = serialPort.getInputStream();
            int availableBytes = 0;
            while(goon){
                availableBytes = is.available();
                while(availableBytes > 0){
                    int data = is.read();
                    FileOperator.writeToRxFile(Integer.toHexString(data));//向文件中写入16数据
                    ShowResultAction.showData(ShowDataUtil.packageBinString(data));//接收数据显示
                    availableBytes = is.available();
                }
//				Thread.sleep(20);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
