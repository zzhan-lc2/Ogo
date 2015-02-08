package com.ogomonkey.security.dao;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "unit", "dao" })
public class Test_CrypterPKCSDao {
    CrypterDao dao;

    @BeforeMethod
    public void init() {
        try {
            dao = new CrypterPKCSDao();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void test_encryptAndDecrypt() {
        String strToBeEncrypted = "OgoMonkey Software";
        String password = "password";
        byte[] salt = "random salt".getBytes();

        PBEncryptStorage encrypt = dao.encrypt(strToBeEncrypted, password, salt);
        Assert.assertNotNull(encrypt);
        System.out.println("[1] Encrypted string [Base64] = " + encrypt);

        String decrypt = dao.decrypt(encrypt, password, salt);
        Assert.assertEquals(strToBeEncrypted, decrypt);
        System.out.println("[2] Decrypted string = " + decrypt);
    }
}
