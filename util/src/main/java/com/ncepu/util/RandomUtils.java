package com.ncepu.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author wengym
 * @version 1.0
 * @desc 随机数工具
 * @date 2023/4/26 13:36
 */
public class RandomUtils {

    /**
     * 获取时间相关的32位随机字符串
     *
     * @param str
     *
     * @author wengym
     *
     * @date 2023/4/26 13:39
     *
     * @return java.lang.String
     */
    public static String getRandomOrderIdByTime(String str) {
        String times = DateUtils.getNowDate(DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS_MIS);
        int len = 32 - times.length();
        if (str != null) {
            len = len - str.length();
        }
        if (len > 0) {
            double random = Math.random() * 7 + 1;
            long num = (long)Math.pow(10, len - 1);
            return str + times + (long)(random * num);
        }
        return (str + times).substring(0, 32);
    }

    /**
     * 返回指定长度的伪随机整数字符串
     *
     * @param len
     * @return
     */
    public static String getRandomNum(int len) {
        // 1.生成len位整数的最大值999...
        StringBuilder max = new StringBuilder();
        for (int i = 0; i < len; i++) {
            max.append("9");
        }
        // 2.生成[0,max]区间中的伪随机整数
        Random rand = new Random();
        // 返回[0,max]之间的整数
        String str = String.valueOf(rand.nextInt(Integer.valueOf(max.toString())));
        StringBuilder result = new StringBuilder();
        // 3.如果伪随机整数的长度小于len，前置补0
        for (int i = 0; i < len - str.length(); i++) {
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

    /**
     * 获取大于或等于0.0且小于1.0的伪随机双精度浮点数。
     *
     * @return
     */
    public static double getRandom() {
        return Math.random();
    }
}
