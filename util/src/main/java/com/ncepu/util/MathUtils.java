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
    /**
     * 向下取整数
     * 如1.4，0.5的结果为：1，0
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2023/12/22 15:15
     *
     * @return java.lang.String
     */
    public static Integer getFloorInt(Double num) {
        Integer result = (int)Math.floor(num);
        return result;
    }

    /**
     * 向上取整数
     * 如1.4，0.5的结果为：2，1
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2023/12/22 15:15
     *
     * @return java.lang.String
     */
    public static Integer getCeilInt(Double num) {
        Integer result = (int)Math.ceil(num);
        return result;
    }

    /**
     * 四舍五入取整数
     * 如1.4，0.5的结果为：1，1
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2023/12/22 15:15
     *
     * @return java.lang.String
     */
    public static Integer getRoundInt(Double num) {
        Integer result = (int)Math.round(num);
        return result;
    }

    /**
     * 获取百分比形式的增长比：将这个月减去上个月然后除以上个月所得就是增长比，如(100-40)/40=150%
     * 如果上期值为0，则直接返回100%。
     * 一般这种情况下，只考虑增长值，因为前期值为0，考虑增长率无意义。
     *
     * @param current 当期值
     *
     * @param last 上期值
     *
     * @author wengym
     *
     * @date 2023/4/13 15:20
     *
     * @return java.lang.String
     */
    public static String getGrowthRatioOfPercent(double current, double last) {
        if (last == 0) {
            return current == 0 ? "0.00%" : "100.00%";
        }
        String flag = "";
        if (current < last) {
            flag = "-";
        }
        double scale = Math.abs(current - last) / last;
        String percent = flag + MathUtils.toFixed(scale * 100, 2) + "%";
        return percent;
    }

    /**
     * 转为指定小数位数的百分数
     *
     * @param num 要转百分数的参数
     *
     * @param scale 保留的小数位数
     *
     * @author wengym
     *
     * @date 2023/12/22 15:12
     *
     * @return java.lang.String
     */
    public static String getPercentNum(double num, int scale) {
        String r1 = toFixed(num * 100, scale);
        return r1 + "%";
    }

    /**
     * 字符串数字转整数
     *
     * @param numStr
     *
     * @author wengym
     *
     * @date 2023/12/22 15:12
     *
     * @return java.lang.Integer
     */
    public static Integer toInteger(String numStr) {
        Integer num = Integer.valueOf(numStr);
        return num;
    }

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

    /***
     * 把字符串数据转化未BigDecimal并四舍五入取指定位小数。
     * 金额需要精确计算，精确计算必须要用String来构造BigDecimal。
     * Java里面的商业计算，不能用float和double，因为它们无法进行精确计算。
     *
     * @param num
     *
     * @param scale
     *
     * @author wengym
     *
     * @date 2022/6/20 11:55
     *
     * @return java.lang.String
     *
     */
    public static String toFixed(String num, int scale) {
        if (CommonUtil.isNullStr(num)) {
            num = "0";
        }
        return new BigDecimal(num).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 要尽量要使用字符串而不是浮点数去构造 BigDecimal 对象，如果实在不行，就使用 BigDecimal#valueOf()方法
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2022/9/19 14:24
     *
     * @return java.math.BigDecimal
     */
    public static BigDecimal getBigDecimal(String num) {
        return new BigDecimal(num);
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
        System.out.println(getRandomNum(2));
        System.out.println(geneRandomNumByRange(10, 20));
        double dou = (getRandom() * 9 + 1) * 100000;
        System.out.println("浮点：" + dou);
        // 取整不是四舍五入，而是直接截断小数部分
        System.out.println("取整：" + (int) dou);

        System.out.println("2的10次幂：" + Math.pow(2, 10));
        System.out.println("2的11次幂：" + Math.pow(2, 11));

        MathUtils.toFixed(1, 1);
        PrintUtils.println("保留两位小数" + MathUtils.toFixed(12.4999999, 3));
    }
}
