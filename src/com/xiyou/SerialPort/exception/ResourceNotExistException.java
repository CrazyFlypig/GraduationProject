package com.xiyou.SerialPort.exception;

/**
 * @author cc
 */

public class ResourceNotExistException extends Exception {
    private static final long serialVersionUID = 6737929750634751137L;

    public ResourceNotExistException() {
    }

    public ResourceNotExistException(String arg0) {
        super(arg0);
    }

    public ResourceNotExistException(Throwable arg0) {
        super(arg0);
    }

    public ResourceNotExistException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ResourceNotExistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
