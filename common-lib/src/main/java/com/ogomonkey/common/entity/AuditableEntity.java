package com.ogomonkey.common.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AuditableEntity implements Serializable, IAuditable<String> {
    private static final long serialVersionUID = 1L;

    private Date creationDate = new Date();
    private String createdBy;
    private Date lastUpdatedDate;
    private String lastUpdatedBy;

}
