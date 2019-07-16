package com.example.lj.utils.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @Description
 */

public class DesUtils12306 {
    /**
     * 设置密钥，略去
     */
    private final byte[] DESkey = "L82V6ZVD6J".getBytes();

    /**
     * 设置向量，略去
     * 加密算法的参数接口，IvParameterSpec是它的一个实现
     */
    private AlgorithmParameterSpec iv = null;
    private Key key = null;

    public DesUtils12306() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(DESkey);
        byte[] keyBytes = new byte[8];
        byte[] ivBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            keyBytes[i] = digest[i];
        }
        for (int i = 8; i < 16; i++) {
            ivBytes[i - 8] = digest[i];
        }
        // 设置密钥参数
        DESKeySpec keySpec = new DESKeySpec(keyBytes);
        // 设置向量
        iv = new IvParameterSpec(ivBytes);
        // 获得密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 得到密钥对象
        key = keyFactory.generateSecret(keySpec);
    }

    public String encode(String data) throws Exception {
        // 得到加密对象Cipher
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 设置工作模式为加密模式，给出密钥和向量
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] pasByte = enCipher.doFinal(data.getBytes());
        return toHexString(pasByte).toUpperCase();
    }


    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }

        return hexString.toString();
    }
}