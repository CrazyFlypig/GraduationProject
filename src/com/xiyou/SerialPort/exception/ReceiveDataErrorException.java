package com.xiyou.SerialPort.exception;

/**
 * @author cc
 */

public class ReceiveDataErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public ReceiveDataErrorException() {
    }

    public ReceiveDataErrorException(String arg0) {
        super(arg0);
    }

    public ReceiveDataErrorException(Throwable arg0) {
        super(arg0);
    }

    public ReceiveDataErrorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ReceiveDataErrorException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
