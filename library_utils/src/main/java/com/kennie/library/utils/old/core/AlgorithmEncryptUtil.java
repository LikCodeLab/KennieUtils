package com.kennie.library.utils.old.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @项目名 KennieUtils
 * @类名称 AlgorithmEncryptUtil
 * @类描述 加密算法
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 18:48
 */
public class AlgorithmEncryptUtil {

    private final static Map<Integer, String> mapList = new HashMap<>();

    public final static int SHA1 = 0;
    public final static int SHA256 = 1;
    public final static int SHA384 = 2;
    public final static int SHA512 = 3;
    public final static int MD5 = 4;

    static {
        mapList.put(SHA1, "SHA-1");
        mapList.put(SHA256, "SHA-256");
        mapList.put(SHA384, "SHA-384");
        mapList.put(SHA512, "SHA-512");
        mapList.put(MD5, "MD5");
    }


    /**
     * 获取字符串的MD5值
     *
     * @param text 字符串
     * @return 字符串的MD5值
     */
    public static String getMD5(String text) {
        return getHash(text, MD5);
    }

    /**
     * 获取文件的MD5值
     *
     * @param file 文件
     * @return 文件MD5值
     */
    public static String getMD5(File file) {
        String hash = "";
        if (file == null || !file.exists()) {
            return hash;
        }
        InputStream is = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Objects.requireNonNull(mapList.get(MD5)));
            is = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            byte[] bytes = messageDigest.digest();
            hash = ByteUtil.byteArray2HexString(bytes);
        } catch (Exception e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return hash;
    }

    /**
     * 获取字符串的Hash值
     *
     * @param text      字符串
     * @param algorithm 加密算法（mapList）
     * @return Hash值
     */
    public static String getHash(String text, int algorithm) {
        String hashStr = "";
        try {
            if (mapList.containsKey(algorithm)) {
                final MessageDigest digest = MessageDigest.getInstance(Objects.requireNonNull(mapList.get(algorithm)));
                final byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
                digest.update(bytes);
                hashStr = ByteUtil.byteArray2HexString(digest.digest());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashStr;
    }

}
