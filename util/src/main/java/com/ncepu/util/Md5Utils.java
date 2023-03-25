package com.ncepu.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串工具类
 *
 * @author wengym
 * @date 2021-11-11
 */
public class Md5Utils {

    /***
     *
     * @param plainText 明文
     *
     * @desc 用MD5算法对字符串进行加密
     *
     * @author wengym
     *
     * @date 2021/11/11 17:53
     *
     * @return 密文
     *
     */
    public static String toMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = md5code.insert(0, "0");
        }
        return md5code.toString();
    }

    public static void main(String[] args) {
        // e10adc3949ba59abbe56e057f20f883e
        System.out.println("MD5加密：" + Md5Utils.toMD5("123456"));
        System.out.println("MD5加密：" + Md5Utils.toMD5("000000"));
        System.out.println("MD5加密：" + Md5Utils.toMD5("66704589"));
        System.out.println("MD5加密：" + Md5Utils.toMD5("Aa123456"));
        // f2394e265bb33d14d6c54682a35e4396
    }
}
