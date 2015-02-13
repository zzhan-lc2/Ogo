package com.ogomonkey.security.entity;

import javax.xml.bind.DatatypeConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ogomonkey.common.entity.AuditableEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaltInfo extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String relatedEntityId;
    private String relatedEntityType;
    private String passwordForAlg;
    private byte[] salt;

    public void setSaltBase64Str(String saltStr) {
        salt = DatatypeConverter.parseBase64Binary(saltStr);
    }

    public String getSaltBase64Str() {
        if (null == salt)
            return null;
        return DatatypeConverter.printBase64Binary(salt);
    }
}
