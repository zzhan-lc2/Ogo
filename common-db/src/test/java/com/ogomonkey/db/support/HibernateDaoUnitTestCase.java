package com.ogomonkey.db.support;

import org.hibernate.SessionFactory;

public class HibernateDaoUnitTestCase extends HibernateDaoTestCase {
    private static boolean isInMemoryDatabaseEnabled = true;

    static final String OGO_DB_SCHEMA = "ofudii";

    @Override
    protected SessionFactory setupSessionFactory() {
        return isInMemoryDatabaseEnabled
            ? new InMemoryPersistenceContext().setSchemaName(OGO_DB_SCHEMA).getSessionFactory()
            : new MysqlUnitTestPersistenceContext().getSessionFactory();
    }

    public static void setIsInMemoryDatabaseEnabled(boolean value) {
        isInMemoryDatabaseEnabled = value;
    }
}
