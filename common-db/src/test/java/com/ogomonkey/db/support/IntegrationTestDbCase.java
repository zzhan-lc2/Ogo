package com.ogomonkey.db.support;

import com.ogomonkey.architect.IntegrationTestCase;

public class IntegrationTestDbCase extends IntegrationTestCase {
    static String[] cniprSpringConfigResources = {
        "ipdev-db.spring.xml"
    };

    public IntegrationTestDbCase() {
        super(cniprSpringConfigResources);
    }
}
