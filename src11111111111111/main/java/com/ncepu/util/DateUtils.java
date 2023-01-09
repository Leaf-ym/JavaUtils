package com.ncepu.util;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 *
 * @author wengym
 * @date 2021-11-2
 */
public class DateUtils {
    
    public static final String FORMAT_YYYY = "yyyy";

    public static final String FORMAT_MM = "MM";

    public static final String FORMAT_dd = "dd";
    /* 时间格式 */
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM_DD_Z = "yyyy年MM月dd日";
    public static final String FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MI_SS_MIS = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYY_MM_DD_HM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_START = "yyyy/MM/dd HH:ss";

    /***
     *
     * 把SQL时间戳转换为指定格式的字符串
     *
     * @param timestamp
     *
     * @param format
     *
     * @author wengym
     *
     * @date 2022/4/15 10:31
     *
     * @return java.lang.String
     *
     */
    public static String sqlTimestampToStr(java.sql.Timestamp timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(new java.util.Date(timestamp.getTime()));
        return result;
    }
    
    /**
     * 当前时间的前一天
     */
    public static String getDateBackOneDay(String format) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        return dateString;
    }
    
    /**
     * 获取当前时间的前n个月
     */
    public static String getDateBackNMonth(String format,int n) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -n);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        return dateString;
    }
    
    /**
     * 把秒数格式化为时分秒
     *
     * @param seconds
     * @return
     */
    public static String formatTimeBySeconds(int seconds){
        int second = seconds % 60;
        int minute = (seconds/60) % 60;
        int hour = seconds/3600;
        System.out.println(hour+"时"+minute+"分"+second+"秒");
        return hour+"时"+minute+"分"+second+"秒";
    }

    /**
     * 获取当前的单位为秒的时间戳（10位）
     *
     * 时间戳的定义：通常是一个字符序列，唯一地标识某一刻的时间。数字时间戳技术是数字签名技术一种变种的应用。是指格林威治时间1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)起至现在的总秒数。
     * 在不同的开发语言中，获取到的时间戳的长度是不同的，例如C++中的时间戳是精确到秒的，但是Java中的时间戳是精确到毫秒的，这样在涉及到不同语言的开发过程中，如果不进行统一则会出现一些时间不准确的问题。
     * Java中的时间戳的毫秒主要通过最后的三位来进行计量的，通过整除1000，可去掉最后三位已得到单位为秒的时间戳
     *
     * @return
     * @author wengym
     * @date 2021-11-2
     */
    public static long getNowSecondTimestamp(){
        // version 1.0
        Calendar calendar = Calendar.getInstance();
        long millis = calendar.getTimeInMillis();

        // version 2.0
        /*long millis = System.currentTimeMillis();*/

        // version 3.0
        /*Date date = new Date();
        long millis = date.getTime();*/

        return millis / 1000;
    }

    /**
     * 获取当前的单位为毫秒的时间戳（13位）
     *
     * @return
     * @author wengym
     * @date 2021-11-2
     */
    public static long getNowMillisTimestamp(){
        /**
         * System.currentTimeMillis();
         * new Date().getTime();
         */
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * 获取两个日期时间的差，以秒为单位（date1 - date2）
     *
     * @param date1
     * @param date2
     * @param format
     * @return
     * @author wengym
     * @date 2021-11-2
     */
    public static long getDiffSecondOfDateTime(String date1, String date2, String format){
        DateFormat sdf = new SimpleDateFormat(format);
        long diff = 0;
        try {
            diff = sdf.parse(date1).getTime() - sdf.parse(date2).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff / 1000;
    }

    /***
     *
     * 比较两个时间的大小，如果第一个时间大于第二个时间返回1，如果第一个时间等于第二个时间返回0，如果第一个时间小于第二个时间返回-1
     *
     * @param dateTime1
     * @param dateTime2
     * @param format
     *
     * @author wengym
     *
     * @date 2022/3/23 9:38
     *
     * @return int
     *
     */
    public static int compareTwoDateTime(String dateTime1, String dateTime2, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        int result = 1;
        try {
            result = (sdf.parse(dateTime1).getTime() - sdf.parse(dateTime2).getTime()) > 0 ? 1: (sdf.parse(dateTime1).getTime() - sdf.parse(dateTime2).getTime()) == 0 ? 0 : -1;
        }  catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     *
     * @param date 时间字符串
     *
     * @param format1 date的原时间格式
     *
     * @param format2 date要转换的时间格式
     *
     * @desc 把时间字符串从format1格式转换为format2格式
     *
     * @author wengym
     *
     * @date 2022/1/12 10:54
     *
     * @return java.lang.String
     *
     */
    public static String getDateStr(String date, String format1, String format2) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(format1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
        try {
            Date date1 = sdf1.parse(date);
            String date2 = sdf2.format(date1);
            return date2;
        } catch (ParseException e) {

        }
        return "";
    }

    /***
     *
     * @param format
     *
     * @param day
     *
     * @desc 获取当前日期的前几天或后几天的日期（day为负数表示前几天，day为整数表示后几天，day为0表示当天时间）
     *
     * @author wengym
     *
     * @date 2022/1/12 11:09
     *
     * @return java.lang.String
     *
     */
    public static String getAfterDayDateStr(String format, int day) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(cal.getTime());
        return str;
    }

    /***
     *
     * @param date 日期
     *
     * @param format 日期格式
     *
     * @desc 验证日期的合法性
     *
     * @author wengym
     *
     * @date 2022/1/4 18:09
     *
     * @return boolean
     *
     */
    public static boolean validateDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.setLenient(false); // 指定日期/时间解析是严格的，true：不严格，false：严格
            sdf.parse(date); // 解析文本，以生成一个日期
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(DateUtils.getDateBackNMonth(DateUtils.FORMAT_YYYY_MM_DD, 2));
        System.out.println(DateUtils.getNowMillisTimestamp());
        DateUtils.formatTimeBySeconds(3600);
        System.out.println("当前秒级的时间戳："+DateUtils.getNowSecondTimestamp());
        System.out.println("时间差："+DateUtils.getDiffSecondOfDateTime("2021-11-02 10:55:30", "2021-11-02 10:54:00", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS));
        System.out.println("时间合法性："+DateUtils.validateDate("2022-12-31", DateUtils.FORMAT_YYYY_MM_DD));
        System.out.println("时间字符串格式化："+DateUtils.getDateStr("2022-01-12 11:01:30", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS, DateUtils.FORMAT_YYYY));
        System.out.println("前几天的时间："+DateUtils.getAfterDayDateStr(DateUtils.FORMAT_YYYY_MM_DD, -14));
        System.out.println("两个时间的比较："+DateUtils.compareTwoDateTime("2022-03-24", "2022-03-23", DateUtils.FORMAT_YYYY_MM_DD));
        System.out.println("格式化sql时间戳："+DateUtils.sqlTimestampToStr(new Timestamp(System.currentTimeMillis()), DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS));
    }
    
}
