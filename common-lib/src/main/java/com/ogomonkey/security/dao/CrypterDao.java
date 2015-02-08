package com.ogomonkey.security.dao;

import java.nio.charset.Charset;

public interface CrypterDao {
    static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * Encrypt the input string with the specified password and salt.
     * 
     * @param inputString
     *            the input string
     * @param password
     *            the password string
     * @param salt
     *            the salt string
     * @return the EncryptResult
     */
    PBEncryptStorage encrypt(String inputString, String password, byte[] salt);

    /**
     * Decrypt the encrypted text with the original password, salt string and the input vector (iv) from encryption
     * stage
     * 
     * @param pbeStorage
     *            the PBEncryptStorage contains cipherText and iv
     * @param password
     *            the password string
     * @param salt
     *            the salt
     * @return the decrypted string
     */
    String decrypt(PBEncryptStorage pbeStorage, String password, byte[] salt);
}
