package com.ogomonkey.common;

public class RecordNotFoundException extends OgoException {
    private static final long serialVersionUID = 1L;

    private String query;

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable e) {
        super(message, e);
    }

    public RecordNotFoundException withQuery(String query) {
        this.query = query;
        return this;
    }

    public String getQuery() {
        return this.query;
    }

}
