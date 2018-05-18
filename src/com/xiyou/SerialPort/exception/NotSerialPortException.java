package com.xiyou.SerialPort.exception;

/**
 * @author cc
 */

public class NotSerialPortException extends Exception {
    private static final long serialVersionUID = -8363352310238354872L;

    public NotSerialPortException() {
    }

    public NotSerialPortException(String arg0) {
        super(arg0);
    }

    public NotSerialPortException(Throwable arg0) {
        super(arg0);
    }

    public NotSerialPortException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public NotSerialPortException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
