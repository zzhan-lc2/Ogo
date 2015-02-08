package com.ogomonkey.common.entity.business;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

import com.ogomonkey.common.entity.AuditableEntity;

@Data
@ToString(exclude = "business")
public class BusinessPolicy extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Business business;
    private String policyType;
    private String shortDescription;
    private String fullText;
    private Date effectiveDate;
    private boolean expired;
}
