package com.ogomonkey.common;

public class OgoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OgoException(String message, Throwable e) {
        super(message, e);
    }

    public OgoException(String message) {
        super(message);
    }
}
