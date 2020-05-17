package com.guofei.wu.weeknine.encryptionalgorithm;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密算法 EncryptionAlgorithm
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/2
 * @since 2018/8/2
 */
public class EncryptionAlgorithm {


    public static void main(String[] args) {
        String str = "123456.";
        System.out.println(hashMd5(str));
    }


    /**
     * MD5
     *
     * @param str
     * @return void
     * @author Mason
     * @since 2018/8/2
     */
    public static String hashMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                String s = Integer.toHexString(b & 0xff);
                if (s.length() == 1) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
