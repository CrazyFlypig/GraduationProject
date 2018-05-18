package com.xiyou.SerialPort.tool;

import com.xiyou.SerialPort.exception.NotSerialPortException;
import com.xiyou.SerialPort.exception.ReadDataFromSerialPortFailureException;
import com.xiyou.SerialPort.exception.SendDataToSerialPortFailureException;
import com.xiyou.SerialPort.exception.SerialPortInputStreamCloseFailureException;
import gnu.io.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * @author cc
 */

public class SerialPortTool {
    private static SerialPortTool serialTool = null;

    static {
        //在该类被ClassLoader加载时就初始化一个SerialTool对象
        if (serialTool == null) {
            serialTool = new SerialPortTool();
        }
    }

    public SerialPortTool() {
    }

    /**
     * 获取提供服务的SerialTool对象
     * @return serialTool
     */
    public static SerialPortTool getSerialTool() {
        if (serialTool == null) {
            serialTool = new SerialPortTool();
        }
        return serialTool;
    }


    /**
     * 查找所有可用端口
     * @return 可用端口名称列表
     */
    public static final ArrayList<String> findPort() {
        //获得当前所有可用串口
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<>();

        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portNameList.add(portId.getName());
            }
        }

        return portNameList;
    }

    /**
     *
     * @param portName 串口名称
     * @param baudrate 波特率
     * @param checkBit 校验位
     * @param stopBit  停止位
     * @return
     * @throws NoSuchPortException
     * @throws PortInUseException
     * @throws UnsupportedCommOperationException
     * @throws NotSerialPortException
     */
    public static final SerialPort openPort(String portName, int baudrate, int stopBit, int checkBit)
            throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, NotSerialPortException {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        CommPort commPort = portIdentifier.open(portName, 2000);

        if(commPort instanceof SerialPort) {
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, stopBit, checkBit);

            return serialPort;
        } else {
            throw new NotSerialPortException("端口指向设备不是串口类型！打开串口操作失败！");
        }
    }

    /**
     * 关闭串口
     * @param serialPort 待关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
        if(serialPort != null) {
            serialPort.removeEventListener();//加的一行
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 给串口中发送数据
     * @param serialPort 串口对象
     * @param order 待发送的数据编码
     * @throws SendDataToSerialPortFailureException 发送数据失败
     */
    public static void sendToPort(SerialPort serialPort, int order) throws SendDataToSerialPortFailureException {
        BufferedOutputStream out = null;

        try {
            out = new BufferedOutputStream(serialPort.getOutputStream());
            out.write(order);
            out.flush();
        } catch (IOException e) {
            throw new SendDataToSerialPortFailureException("向串口发送数据失败！");
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new SendDataToSerialPortFailureException("向串口发送数据失败！");
                }
            }
        }
    }

    /**
     * 读取串口中的数据
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据
     * @throws SerialPortInputStreamCloseFailureException  从串口读取数据时出错
     * @throws ReadDataFromSerialPortFailureException  关闭串口对象输入流出错
     */
    public static int readFromPort(SerialPort serialPort)
            throws SerialPortInputStreamCloseFailureException, ReadDataFromSerialPortFailureException {
        BufferedInputStream fin = null;
        int data;

        try {
            fin = new BufferedInputStream(serialPort.getInputStream());
            data = fin.read();
        } catch (IOException e) {
            throw new ReadDataFromSerialPortFailureException("从串口读取数据时出错！");
        } finally {
            if(fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }

    /**
     * 读取串口数据(String)
     * @param serialPort
     * @return
     */
    public static byte[] readFromPortToString(SerialPort serialPort) {
        InputStream fin = null;
        byte[] bytes = null;

        try {
            fin = serialPort.getInputStream();
            int bufferLength = fin.available();

            while(bufferLength != 0) {
                bytes = new byte[bufferLength];
                fin.read(bytes);
                bufferLength = fin.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bytes;
    }

    /**
     * 添加监听器
     * @param port     串口对象
     * @param listener 串口监听器
     * @throws TooManyListeners 监听类对象过多
     */
    public static void addListener(SerialPort port, SerialPortEventListener listener) throws TooManyListenersException {
        try {
            //给串口添加监听器
            port.addEventListener(listener);
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {
            throw new TooManyListenersException("串口监听类数量过多！添加操作失败！");
        }
    }
}
