package com.xiyou.SerialPort.tool;

import com.xiyou.SerialPort.action.CreatFileAction;

import java.io.*;

/**
 * @author cc
 */

public class FileOperator {
    /**
     * 创建文件
     * @param filename
     * @throws Exception
     */
    public static void creat(String filename) throws Exception {
        File file = new File(filename);
        if(!file.exists()) {
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }

            file.createNewFile();
            //	System.out.println("文件创建成功！");
        }
    }


    private static BufferedWriter rx_out = null;
    private static OutputStreamWriter rx_outputStreamWriter = null;
    private static FileOutputStream rx_fileOutputStream = null;

    private static BufferedWriter tx_out = null;
    private static OutputStreamWriter tx_outputStreamWriter = null;
    private static FileOutputStream tx_fileOutputStream = null;

    /**
     * 设定rx_file文件路径
     * @param filename
     * @throws FileNotFoundException
     */
    public static void setTargetRxFile() throws FileNotFoundException {
        File file = new File(CreatFileAction.getFullPath_RxFile());

        rx_fileOutputStream = new FileOutputStream(file, true);
        rx_outputStreamWriter = new OutputStreamWriter(rx_fileOutputStream);
        rx_out = new BufferedWriter(rx_outputStreamWriter);
    }

    /**
     * 设置tx_file文件路径
     * @param filename
     * @throws FileNotFoundException
     */
    public static void setTargetTxFile() throws FileNotFoundException {
        File file = new File(CreatFileAction.getFullPath_TxFile());

        tx_fileOutputStream = new FileOutputStream(file, true);
        tx_outputStreamWriter = new OutputStreamWriter(tx_fileOutputStream);
        tx_out = new BufferedWriter(tx_outputStreamWriter);
    }

    /**
     * 向rx文件写入内容
     * @param content
     * @throws IOException
     */
    public static void writeToRxFile(String content) throws IOException {
        rx_out.write(content+"\r\n");
    }

    /**
     * 向tx文件写入内容
     * @param content
     * @throws IOException
     */
    public static void writeToTxFile(String content) throws IOException {
        tx_out.write(content+"\r\n");
    }

    /**
     * 关闭所有资源
     */
    public static void closeResource() {
        if(tx_out != null) {
            try {
                tx_out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tx_outputStreamWriter != null) {
            try {
                tx_outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tx_fileOutputStream != null) {
            try {
                tx_fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(rx_out != null) {
            try {
                rx_out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(rx_outputStreamWriter != null) {
            try {
                rx_outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(rx_fileOutputStream != null) {
            try {
                rx_fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
