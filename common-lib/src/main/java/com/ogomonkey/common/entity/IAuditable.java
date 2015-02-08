package com.ogomonkey.common.entity;

import java.util.Date;

public interface IAuditable<T> {

    void setCreatedBy(T createdBy);

    T getCreatedBy();

    void setCreationDate(Date creationDate);

    Date getCreationDate();

    void setLastUpdatedBy(T lastUpdatedBy);

    T getLastUpdatedBy();

    void setLastUpdatedDate(Date lastUpdatedDate);

    Date getLastUpdatedDate();
}
