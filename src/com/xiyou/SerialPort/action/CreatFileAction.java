package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.tool.FileOperator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cc
 */

public class CreatFileAction {
    private static final String sendFilePrefix = "uart_tx";
    private static final String receiveFilePrefix = "uart_rx";

    private static final String filePath = "./recordFile";

    private static String tx_filename;
    private static String rx_filename;

    /**
     * 创建两个文件
     */
    public static void creat() {
        try {
            //根据系统时间创建两个文件
            String time = getTimeNow();
            tx_filename =  sendFilePrefix + "_" + time + ".txt";
            rx_filename =  receiveFilePrefix + "_" + time + ".txt";

            //创建文件
            FileOperator.creat(filePath + "/" + tx_filename);
            FileOperator.creat(filePath + "/" + rx_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTimeNow() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");//可以方便地修改日期格式
        return dateFormat.format(now);
    }

    public static String getTx_filename() {
        return tx_filename;
    }

    public static String getRx_filename() {
        return rx_filename;
    }

    public static String getFullPath_TxFile() {
        return filePath + "/" + tx_filename;
    }

    public static String getFullPath_RxFile() {
        return filePath + "/" + rx_filename;
    }
}
