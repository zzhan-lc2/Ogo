package com.ogomonkey.db.util;

import java.util.Date;

public abstract class AuditableBuilder {
    protected Date creationDate;
    protected String createdBy;
    protected Date lastUpdatedDate;
    protected String lastUpdatedBy;

}
