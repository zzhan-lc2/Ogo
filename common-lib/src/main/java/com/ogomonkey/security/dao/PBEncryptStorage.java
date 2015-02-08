package com.ogomonkey.security.dao;

import javax.xml.bind.DatatypeConverter;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

@Getter
public class PBEncryptStorage {
    static final String SEPARATOR = " *** ";

    byte[] iv;
    byte[] cipherText;

    public PBEncryptStorage(byte[] iv, byte[] ciphertext) {
        this.iv = iv;
        this.cipherText = ciphertext;
    }

    public PBEncryptStorage(String base64) {
        this(base64, SEPARATOR);
    }

    public PBEncryptStorage(String base64, String separator) {
        int loc = base64.indexOf(separator);

        iv = DatatypeConverter.parseBase64Binary(StringUtils.substring(base64, 0, loc));
        cipherText = DatatypeConverter.parseBase64Binary(StringUtils.substring(base64, loc + separator.length()));
    }

    @Override
    public String toString() {
        return DatatypeConverter.printBase64Binary(iv) + SEPARATOR +
            DatatypeConverter.printBase64Binary(cipherText);
    }
}
