package com.xiyou.SerialPort.exception;

/**
 * @author cc
 */

public class SendDataToSerialPortFailureException extends Exception {
    private static final long serialVersionUID = 5224413092230314904L;

    public SendDataToSerialPortFailureException() {
    }

    public SendDataToSerialPortFailureException(String arg0) {
        super(arg0);
    }

    public SendDataToSerialPortFailureException(Throwable arg0) {
        super(arg0);
    }

    public SendDataToSerialPortFailureException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public SendDataToSerialPortFailureException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
