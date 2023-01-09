/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncepu.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 数学工具类
 *
 * @author 123
 */
public class MathUtils {

    public static double roundForNumber(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 返回指定长度的伪随机整数字符串
     *
     * @param len
     * @return
     */
    public static String geneRandomNumByLen(int len) {
        // 1.生成len位整数的最大值999...
        StringBuilder max = new StringBuilder();
        for (int i = 0; i < len; i++) {
            max.append("9");
        }
        // 2.生成[0,max]区间中的伪随机整数
        Random rand = new Random();
        String str = String.valueOf(rand.nextInt(Integer.valueOf(max.toString()))); // 返回[0,max]之间的整数
        StringBuilder result = new StringBuilder();
        // 3.如果伪随机整数的长度小于len，前置补0
        for (int i = 0; i < len - str.length(); i++) { //
            result.append('0');
        }
        // 4.返回指定长度的伪随机整数字符串
        return result.toString() + str;
    }

    /**
     * 返回[min,max]区间中的伪随机整数字符串
     *
     * @param min
     * @param max
     * @return
     */
    public static String geneRandomNumByRange(int min, int max) {
        /**
         * random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
         * 以生成[10,20]随机数为例，首先生成0-20的随机数，然后对(20-10+1)取模得到[0-10]之间的随机数，
         * 然后加上min=10，最后生成的是10-20的随机数
         */
        Random random = new Random();
        String result = String.valueOf(random.nextInt(max) % (max - min + 1) + min);
        return result;
    }

    /***
     *
     * 四舍五入取值，并保留指定位小数
     *
     * @param num
     * @param scale
     *
     * @author wengym
     *
     * @date 2022/3/26 13:39
     *
     * @return java.lang.String
     *
     */
    public static String toFixed(double num, int scale) {
        String result = String.format("%." + scale + "f", num);
        return result;
    }

    /**
     * 获取大于或等于0.0且小于1.0的伪随机双精度浮点数。
     *
     * @return
     */
    public static double getRandom() {
        return Math.random();
    }

    public static void main(String[] args) {
        System.out.println(geneRandomNumByLen(2));
        System.out.println(geneRandomNumByRange(10, 20));
        double dou = (getRandom() * 9 + 1) * 100000;
        System.out.println("浮点：" + dou);
        System.out.println("取整：" + (int) dou); // 取整不是四舍五入，而是直接截断小数部分

        System.out.println("2的10次幂：" + Math.pow(2, 10));
        System.out.println("2的11次幂：" + Math.pow(2, 11));

        MathUtils.toFixed(1, 1);
        PrintUtils.println("保留两位小数" + MathUtils.toFixed(12.4999999, 3));
    }
}
