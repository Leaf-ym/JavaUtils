package com.ncepu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wengym
 * @version 1.0
 * @desc 流水号工具类
 * @date 2022/9/26 17:47
 */
public class SerialNumUtils {

    private static String MSSGSRCID = "7173";

    /**
     * 获取当前时间并格式化到毫秒
     *
     * @author wengym
     *
     * @date 2022/9/26 17:51
     *
     * @return java.lang.String
     */
    public static String getMillisTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formatStr = formatter.format(new Date());
        return formatStr;
    }


    /**
     * 生成流水订单号（27位）
     * 构成：4位指定数字 + 1位标志数字 + 17位时间戳 + 5位随机数字
     * 不能超过32位
     *
     * @param
     *
     * @author wengym
     *
     * @date 2022/9/26 17:52
     *
     * @return java.lang.String
     */
    public static String getOrderIdByTime(String type) {
        String str = getMillisTime();
        int num = (int) ((Math.random() * 7 + 1) * 10000);
        return MSSGSRCID + getFlagBit(type) + str + num;
    }

    /**
     * 获取标志位
     *
     * @param type
     *
     * @author wengym
     *
     * @date 2022/9/26 17:57
     *
     * @return java.lang.String
     */
    public static String getFlagBit(String type) {
        if ("MEMBER".equals(type)) {
            return "1";
        } else if ("MEET".equals(type)) {
            return "0";
        }
        return "0";
    }

    /**
     * 获取字符串特定索引处的字符
     *
     * @param str
     *
     * @param index 大于等于0
     *
     * @author wengym
     *
     * @date 2022/9/26 18:01
     *
     * @return java.lang.String
     */
    public static String getChar(String str, Integer index) {
        String c = "";
        if (str == null || index == null || index < 0) {
            return c;
        }
        if (str.length() < index + 1) {
            return c;
        }
        c = str.substring(index, index + 1);
        return c;
    }
}
