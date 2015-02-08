package com.ogomonkey.common.entity.finance;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.entity.AuditableEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class Instrument extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private InstrumentType type;
    private String name;
    private String number; // like CC number; bank account number; etc
    private String securityCode; // like CVN
    private boolean numberEncrypted; // apply to both "number" and "securityCode"
    private Date expirationDate;
    private EntityStatus signupStatus;
    private Date statusDate;
    private Address billingAddress;
    private String notes;
}
