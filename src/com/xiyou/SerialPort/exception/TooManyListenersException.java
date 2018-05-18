package com.xiyou.SerialPort.exception;

/**
 * @author cc
 */

public class TooManyListenersException extends Exception {
    private static final long serialVersionUID = 267186080499519703L;

    public TooManyListenersException() {
    }

    public TooManyListenersException(String arg0) {
        super(arg0);
    }

    public TooManyListenersException(Throwable arg0) {
        super(arg0);
    }

    public TooManyListenersException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public TooManyListenersException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
