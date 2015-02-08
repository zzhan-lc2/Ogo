package com.ogomonkey.security.dao;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * Password-based encryption (PKCS #5). To change the salt or number of iterations, set the static properties before
 * creating an instance.
 * 
 * To avoid a runtime error, the user must download "Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy
 * Files 6" and place the two java files in <JRE>/lib/security (over top of the default ones). And similarly for
 * debugging: <JDK>/jre/lib/security
 * 
 * @link http://www.oracle.com/technetwork/java/javase/downloads/index.html
 * 
 * @see http://www.ietf.org/rfc/rfc2898.txt
 * @see http://stackoverflow.com/questions/992019/java-256bit-aes-encryption/992413#992413
 */
public class CrypterPKCSDao implements CrypterDao {

    // bytes used to salt the key (set before making an instance)
    static byte[] DEFAULT_SALT = {
        (byte) 0xc8, (byte) 0x73, (byte) 0x41, (byte) 0x8c,
        (byte) 0x7e, (byte) 0xd8, (byte) 0xee, (byte) 0x89
    };

    // number of times the password & salt are hashed during key creation (set before making an instance)
    static int NUM_ITERATIONS = 1024;

    private SecretKey secretKey = null;
    private Cipher cipher = null;

    public CrypterPKCSDao() throws NoSuchAlgorithmException, NoSuchPaddingException {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    @Override
    public PBEncryptStorage encrypt(String inputString, String password, byte[] salt) {
        try {
            initByPassword(password, salt);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return new PBEncryptStorage(
                cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV(),
                cipher.doFinal(inputString.getBytes(DEFAULT_CHARSET)));
        } catch (InvalidKeyException | InvalidParameterSpecException | IllegalBlockSizeException | BadPaddingException
            | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new EncryptionException("Failed to encrypt string [" + inputString + "]", e);
        }
    }

    @Override
    public String decrypt(PBEncryptStorage pbeStorage, String password, byte[] salt) {
        try {
            initByPassword(password, salt);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(pbeStorage.getIv()));
            byte[] plainText = cipher.doFinal(pbeStorage.getCipherText());
            return StringUtils.toEncodedString(plainText, DEFAULT_CHARSET);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
            | BadPaddingException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new DecryptionException("Failed to decrypt PBEStorage [" + pbeStorage + "]", e);
        }
    }

    void initByPassword(String password, byte[] salt) throws InvalidKeySpecException,
        NoSuchAlgorithmException,
        NoSuchPaddingException
    {
        if (salt == null) {
            salt = DEFAULT_SALT;
        }

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, NUM_ITERATIONS, 256);
        secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }

}
