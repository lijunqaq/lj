package com.yizhu.lj.utils.utils.SignEncrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gongdejun on 2017/11/10.
 */
public class MD5UtilHelper {

    /**
     * 获取大写的MD5签名结果
     *
     * @param encypStr 加密字符串
     * @param charset  编码方式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getMD5(String encypStr, String charset) throws UnsupportedEncodingException {
        charset = "UTF-8";
        ////创建md5对象
        byte[] inputBye;
        byte[] outputBye;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
            byte[] value = encypStr.getBytes("UTF-8");
            outputBye = md5.digest(value);
            String retStr = printHexString(outputBye);
            return retStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * byte数组转换为十六进制的字符串
     *
     * @param bytes 需要转换的数据
     * @return
     */
    public static String printHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            stringBuilder.append(hex.toUpperCase());
        }
        return stringBuilder.toString();
    }

    /**
     * 生成MD5.后面再看看。和上面的加密出来是不是一样
     *
     * @param message
     * @return
     */
    public static String getMD5ByNow(String message) {
        String md5 = "";
        try {
            // 创建一个md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageByte = message.getBytes("UTF-8");
            // 获得MD5字节数组,16*8=128位
            byte[] md5Byte = md.digest(messageByte);
            // 转换为16进制字符串
            md5 = bytesToHex(md5Byte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
}
