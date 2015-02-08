package com.ogomonkey.db.support;

import org.hibernate.SessionFactory;

public interface PersistenceContext {
    SessionFactory getSessionFactory();
}
