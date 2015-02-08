package com.ogomonkey.security.dao;

public class DecryptionException extends SecurityException {
    private static final long serialVersionUID = 1L;

    public DecryptionException(String message, Throwable e) {
        super(message, e);
    }
}
