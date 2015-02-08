package com.ogomonkey.common.entity;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.common.collect.Sets;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.datatype.RiskStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class NamedEntity extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private EntityStatus signupStatus;
    private Date signupDate;
    private RiskStatus riskStatus;
    private Date riskStatusDate;
    private Address address;
    private Set<CommEntity> communications;

    public void addCommunication(CommEntity communication) {
        if (null == communications) {
            communications = Sets.newHashSet();
        }
        communications.add(communication);
    }

}
