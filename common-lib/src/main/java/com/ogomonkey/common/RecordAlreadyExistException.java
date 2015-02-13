package com.ogomonkey.common;

public class RecordAlreadyExistException extends OgoException {
    private static final long serialVersionUID = 1L;

    public RecordAlreadyExistException(String message) {
        super(message);
    }

    public RecordAlreadyExistException(String message, Throwable e) {
        super(message, e);
    }
}
