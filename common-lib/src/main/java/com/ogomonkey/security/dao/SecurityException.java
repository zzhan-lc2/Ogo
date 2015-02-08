package com.ogomonkey.security.dao;

public class SecurityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SecurityException(String message, Throwable e) {
        super(message, e);
    }

    public SecurityException(String message) {
        super(message);
    }
}
