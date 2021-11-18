package com.kennie.library.utils.core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @项目名 KennieUtils
 * @类名称 AlgorithmDESUtil
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 22:02
 */
public class AlgorithmDESUtil {

    private final static String DES = "DES";
    // DES/ECB/NoPadding
    private final static String CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";


    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(DES);
        kg.init(16);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(dks));
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static byte[] decrypt(byte[] src, byte[] key) {
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(dks), sr);
            return cipher.doFinal(src);
        } catch (Exception e) {
        }
        return null;
    }
}
