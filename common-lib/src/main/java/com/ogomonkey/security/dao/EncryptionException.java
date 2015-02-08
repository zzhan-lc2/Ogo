package com.ogomonkey.security.dao;

public class EncryptionException extends SecurityException {
    private static final long serialVersionUID = 1L;

    public EncryptionException(String message, Throwable e) {
        super(message, e);
    }
}
