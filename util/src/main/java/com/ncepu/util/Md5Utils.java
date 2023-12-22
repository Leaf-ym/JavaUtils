package com.ncepu.util;

import org.apache.shiro.crypto.hash.SimpleHash;

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

    /**
     * 更安全的一种md5算法
     *
     * @param plainText
     *
     * @param salt
     *
     * @author wengym
     *
     * @date 2023/12/18 16:42
     *
     * @return java.lang.String
     */
    public static String toNewMd5(String plainText, String salt) {
        String password = new SimpleHash("MD5", plainText, salt, 1024).toHex();
        return password;
    }

    public static void main(String[] args) {
        // e10adc3949ba59abbe56e057f20f883e
        System.out.println("MD5加密：" + Md5Utils.toMD5("123456"));
        System.out.println("MD5加密：" + Md5Utils.toMD5("000000"));
        System.out.println("MD5加密：" + Md5Utils.toMD5("66704589"));
        // afdd0b4ad2ec172c586e2150770fbf9e
        System.out.println("MD5加密：" + Md5Utils.toMD5("Aa123456"));
        System.out.println("MD5加密：" + Md5Utils.toMD5("Zz123456"));

        System.out.println("新MD5加密：" + Md5Utils.toNewMd5("Aa123456", "zklcRY"));
    }
}
