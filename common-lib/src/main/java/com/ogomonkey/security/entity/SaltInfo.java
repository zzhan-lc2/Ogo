package com.ogomonkey.security.entity;

import javax.xml.bind.DatatypeConverter;

import lombok.Data;

import com.ogomonkey.security.dao.CryptInfoType;

@Data
public class SaltInfo {
    private String relatedEntityId;
    private CryptInfoType infoType;
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
