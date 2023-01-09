package com.ncepu.util;

/**
 * @author wengym
 * @version 1.0
 * @desc 数字原码、反码和补码的转换
 * @date 2022/10/24 14:25
 */
public class NumCodeUtils {
    public static String toAntiCode(int num) {
return null;
    }
    public static String toOriginalCode(int num) {
        String binary = toBinaryString(num);
        StringBuilder sb = new StringBuilder(binary);
        if (binary.length() < 32) {
            for (int i = 0; i < 32 - binary.length(); i++) {
                sb.insert(0, "0");
            }
            binary = sb.toString();
        }
        return binary;
    }

    /**
     * 转十六进制
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2022/10/24 15:33
     *
     * @return java.lang.String
     */
    public static String toHexString(int num) {
        String hex = Integer.toHexString(num);
        return hex;
    }

    /**
     * 转二进制
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2022/10/24 15:33
     *
     * @return java.lang.String
     */
    public static String toBinaryString(int num) {
        String binary = Integer.toBinaryString(num);
        return binary;
    }

    /**
     * 转八进制
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2022/10/24 15:33
     *
     * @return java.lang.String
     */
    public static String toOctalString(int num) {
        String octal = Integer.toOctalString(num);
        return octal;
    }
}
