package com.xiyou.SerialPort.exception;

/**
 * @author cc
 */

public class SerialPortInputStreamCloseFailureException extends Exception {
    private static final long serialVersionUID = -4535186271364641764L;

    public SerialPortInputStreamCloseFailureException() {
    }

    public SerialPortInputStreamCloseFailureException(String arg0) {
        super(arg0);
    }

    public SerialPortInputStreamCloseFailureException(Throwable arg0) {
        super(arg0);
    }

    public SerialPortInputStreamCloseFailureException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public SerialPortInputStreamCloseFailureException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
