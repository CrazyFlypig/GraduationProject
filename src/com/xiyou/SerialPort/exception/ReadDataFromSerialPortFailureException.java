package com.xiyou.SerialPort.exception;

/**
 * @author cc
 */

public class ReadDataFromSerialPortFailureException extends Exception {
    private static final long serialVersionUID = 2697159965846158747L;

    public ReadDataFromSerialPortFailureException() {
    }

    public ReadDataFromSerialPortFailureException(String arg0) {
        super(arg0);
    }

    public ReadDataFromSerialPortFailureException(Throwable arg0) {
        super(arg0);
    }

    public ReadDataFromSerialPortFailureException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ReadDataFromSerialPortFailureException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
