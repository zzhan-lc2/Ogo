package com.ogomonkey.db.support;

import com.ogomonkey.common.OgoException;

public class OgoDaoException extends OgoException {
    private static final long serialVersionUID = 1L;

    public OgoDaoException(String message) {
        super(message);
    }

    public OgoDaoException(String message, Throwable e) {
        super(message, e);
    }
}
