package com.xiyou.SerialPort.listener;

import com.xiyou.SerialPort.tool.FileOperator;
import com.xiyou.SerialPort.tool.ShowDataUtil;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 */

public class PollingListener implements Runnable {
    private SerialPort serialPort = null;
    private boolean goon = true;
    private InputStream is = null;
    private List<Integer> dataList = new ArrayList<>();

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
            if (is != null) {
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
            int count = 0;
            while (goon) {
                Thread.sleep(100);
                availableBytes = is.available();
                count++;
                if (count > 100) {
                    count = 0;
                    ShowDataUtil.createParamValue(null);
                }
                while (availableBytes > 0) {
                    count = 0;
                    System.out.println("read data");
                    int data = is.read();
                    System.out.println(data);
                    Thread.sleep(1000);
                    dataList.add(data);
                    if (dataList.size() == 2) {

                        FileOperator.writeToRxFile(Integer.toHexString(data));//向文件中写入16数据
                        ShowDataUtil.createParamValue(dataList);
                        dataList.clear();
                    }

                    availableBytes = is.available();
                }
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
