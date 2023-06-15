package com.ncepu.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
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
    public static final String FORMAT_YYYY_MM = "yyyy-MM";

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
    /**
     * 每个月份的天数
     */
    public static final Integer[] MONTH_MAX_DAY = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 获取四个数的年份字符串
     *
     * @param date
     *
     * @param format
     *
     * @author wengym
     *
     * @date 2023/4/13 16:00
     *
     * @return java.lang.String
     */
    public static String getYearStr(String date, String format) {
        String result = formatDate(date, format, DateUtils.FORMAT_YYYY);
        return result;
    }

    /**
     * 获取四个数的年份加两位数的月份字符串
     *
     * @param date
     *
     * @param format
     *
     * @author wengym
     *
     * @date 2023/4/13 16:00
     *
     * @return java.lang.String
     */
    public static String getYearMonthStr(String date, String format) {
        String result = formatDate(date, format, DateUtils.FORMAT_YYYY_MM);
        return result;
    }

    /**
     * 格式化字符串时间
     *
     * @param date 字符串时间
     *
     * @param fromFormat date的格式
     *
     * @param toFormat 要转化的格式
     *
     * @author wengym
     *
     * @date 2023/4/13 16:04
     *
     * @return java.lang.String
     */
    public static String formatDate(String date, String fromFormat, String toFormat) {
        SimpleDateFormat fromSdf = new SimpleDateFormat(fromFormat);
        SimpleDateFormat toSdf = new SimpleDateFormat(toFormat);
        try {
            String result = toSdf.format(fromSdf.parse(date));
            return result;
        } catch (ParseException e) {
        }
        return "";
    }

    /**
     * 返回指定格式的当前时间
     *
     * @param format
     *
     * @author wengym
     *
     * @date 2023/3/23 10:19
     *
     * @return java.lang.String
     */
    public static String getNowDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 获取月份
     *
     * @param date
     * @param format
     * @return java.lang.String
     * @author wengym
     * @date 2023/1/16 15:09
     */
    public static Integer getMonth(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Integer month = 0;
        try {
            Calendar cal = sdf.getCalendar();
            cal.setTime(sdf.parse(date));
            month = cal.get(Calendar.MONTH);
            // 月份是从0开始算起的，所以0为1月，1为2月，11为12月
            month = month + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return month;
    }

    /**
     * 获取季度
     *
     * @param date
     * @param format
     * @return java.lang.String
     * @author wengym
     * @date 2023/1/16 15:09
     */
    public static Integer getQuarter(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return null;
        }
        Integer month = getMonth(date, format);
        // 向上取整，获取季度
        int quarter = (int) Math.ceil(month / 3.0);
        return quarter;
    }

    /**
     * 获取年份
     *
     * @param date
     * @param format
     * @return java.lang.String
     * @author wengym
     * @date 2023/1/16 15:09
     */
    public static Integer getYear(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Integer year = 0;
        try {
            Calendar cal = sdf.getCalendar();
            cal.setTime(sdf.parse(date));
            year = cal.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }

    /**
     * 指定时间相比当前时间是否超时了minutes分钟，超时了为true，不超时则为false
     *
     * @param datetime
     * @param minutes
     * @return boolean
     * @author wengym
     * @date 2022/9/26 15:22
     */
    public static boolean compareWithNow(String datetime, Integer minutes) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime((sdf.parse(datetime, new ParsePosition(0))));
        calendar.add(Calendar.MINUTE, minutes);
        long now = System.currentTimeMillis();
        if (now > calendar.getTime().getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 获取有效开始日期
     *
     * @return java.lang.String
     * @author wengym
     * @date 2022/9/7 13:39
     */
    public static String getStartDate() {
        String startDate = DateUtils.getNowYear() + "-01-01";
        return startDate;
    }

    /**
     * 获取有效结束日期
     *
     * @return java.lang.String
     * @author wengym
     * @date 2022/9/7 13:39
     */
    public static String getEndDate(int year) {
        String endDate = (DateUtils.getNowYear() + year - 1) + "-12-31";
        return endDate;
    }

    /**
     * 获取当前年份
     *
     * @return int
     * @author wengym
     * @date 2022/9/7 13:55
     */
    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 判断日期合法性
     *
     * @param
     * @return
     * @author wengym
     * @date 2022/8/22 11:55
     */
    public static Boolean validateDate(Integer year, Integer month, Integer day) {
        // 判断年份是否合法
        if (year < 1900 || year > 2100) {
            return false;
        }
        // 判断月份是否合法
        if (month < 1 || month > 12) {
            return false;
        }
        // 判断是否是闰年
        if (isLeapYear(year)) {
            // 闰年
            if (month == 2 && day <= 29) {
                // 闰月：判断当前月份是否为2月，因为闰年的2月份为29天
                return true;
            }
            // 非闰月
            if (day <= MONTH_MAX_DAY[month - 1]) {
                return true;
            }
        }
        // 平年
        if (day <= MONTH_MAX_DAY[month - 1]) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是闰年
     * <p>
     * 普通闰年：公历年份是4的倍数，且不是100的倍数的，为闰年（如2004年、2020年等就是闰年）。
     * 世纪闰年：公历年份是整百数的，必须是400的倍数才是闰年（如1900年不是闰年，2000年是闰年）。
     * <p>
     * 1.普通年份能被4整除，且不能被100整除的，是闰年。（如2004年就是闰年）
     * 2.世纪年份能被400整除的是闰年。（如2000年是闰年，1900年不是闰年）
     * 3.对于数值很大的年份，这年如果能被3200整除，并且还能被172800整除的才是闰年。如172800年是闰年，86400年不是闰年（因为虽然能整除3200，但不能整除172800）。（此按一回归年365天5h48'45.5''计算）
     *
     * @param year
     * @return java.lang.Boolean
     * @author wengym
     * @date 2022/8/22 13:41
     */
    public static Boolean isLeapYear(Integer year) {
        Boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 && year % 3200 != 0) || (year % 172800 == 0);
        return isLeapYear;
    }

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
    public static String sqlTimestampToStr(Timestamp timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(new Date(timestamp.getTime()));
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
     * 获取当前时间的num秒后的时间
     *
     * @param format
     * @param num 负数为前，正数往后，0为当前
     * @return java.lang.String
     * @author wengym
     * @date 2023/4/18 16:30
     */
    public static String getNowDateAfterNumSecond(String format, int num) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, num);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        return dateString;
    }

    /**
     * 获取当前时间的num个月的时间
     *
     * @param format
     * @param num
     * @return java.lang.String
     * @author wengym
     * @date 2022/9/27 16:30
     */
    public static String getNowDateAfterNumMonth(String format, int num) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        return dateString;
    }

    /**
     * 获取当前时间的num年后的时间
     *
     * @param format
     * @param num
     * @return java.lang.String
     * @author wengym
     * @date 2022/9/27 16:24
     */
    public static String getNowDateAfterNumYear(String format, int num) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, num);
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
    public static String formatTimeBySeconds(int seconds) {
        int second = seconds % 60;
        int minute = (seconds / 60) % 60;
        int hour = seconds / 3600;
        System.out.println(hour + "时" + minute + "分" + second + "秒");
        return hour + "时" + minute + "分" + second + "秒";
    }

    /***
     *
     * 获取时间date的秒级时间戳
     *
     * @param date
     *
     * @param format
     *
     * @author wengym
     *
     * @date 2022/6/15 13:35
     *
     * @return long
     *
     */
    public static long getSecondTimestamp(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前的单位为秒的时间戳（10位）
     * <p>
     * 时间戳的定义：通常是一个字符序列，唯一地标识某一刻的时间。数字时间戳技术是数字签名技术一种变种的应用。是指格林威治时间1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)起至现在的总秒数。
     * 在不同的开发语言中，获取到的时间戳的长度是不同的，例如C++中的时间戳是精确到秒的，但是Java中的时间戳是精确到毫秒的，这样在涉及到不同语言的开发过程中，如果不进行统一则会出现一些时间不准确的问题。
     * Java中的时间戳的毫秒主要通过最后的三位来进行计量的，通过整除1000，可去掉最后三位已得到单位为秒的时间戳
     *
     * @return
     * @author wengym
     * @date 2021-11-2
     */
    public static long getNowSecondTimestamp() {
        Calendar calendar = Calendar.getInstance();
        long millis = calendar.getTimeInMillis();
        return millis / 1000;
    }

    /**
     * 获取当前的单位为毫秒的时间戳（13位）
     *
     * @return
     * @author wengym
     * @date 2021-11-2
     */
    public static long getNowMillisTimestamp() {
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
    public static long getDiffSecondOfDateTime(String date1, String date2, String format) {
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
            result = (sdf.parse(dateTime1).getTime() - sdf.parse(dateTime2).getTime()) > 0 ? 1 : (sdf.parse(dateTime1).getTime() - sdf.parse(dateTime2).getTime()) == 0 ? 0 : -1;
        } catch (ParseException e) {
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
        //设置起时间
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(cal.getTime());
        return str;
    }

    /**
     * 获取指定日期的前几天或后几天的日期（day为负数表示前几天，day为整数表示后几天，day为0表示当天时间）
     *
     * @param format
     *
     * @param date
     *
     * @param day
     *
     * @author wengym
     *
     * @date 2023/5/16 11:25
     *
     * @return java.lang.String
     */
    public static String getAfterDayDateStr(String format, String date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date hDate = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(hDate);
            cal.add(Calendar.DAY_OF_YEAR, day);
            String str = sdf.format(cal.getTime());
            return str;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取昨天的日期
     *
     * @param format
     * @return java.lang.String
     * @author wengym
     * @date 2022/10/11 20:15
     */
    public static String getYesterdayDateStr(String format) {
        String str = getAfterDayDateStr(format, -1);
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
            // 指定日期/时间解析是严格的，true：不严格，false：严格
            sdf.setLenient(false);
            // 解析文本，以生成一个日期
            sdf.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 检查指定时间和当前时间
     * 指定时间 > 当前时间：1， 2099-01-01（指定时间） > 2000-01-01（当前时间）
     * 指定时间 < 当前时间：-1
     * 指定时间 = 当前时间：0
     *
     * @param date
     *
     * @param format
     *
     * @author wengym
     *
     * @date 2023/6/5 14:43
     *
     * @return int
     */
    public static int checkDateWithNow(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date theDate = sdf.parse(date, new ParsePosition(0));
        Date nowDate =  sdf.parse(sdf.format(new Date()), new ParsePosition(0));
        if (nowDate.before(theDate)) {
            return 1;
        }
        if (nowDate.after(theDate)) {
            return -1;
        }
        return 0;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(DateUtils.getNowDateAfterNumMonth(DateUtils.FORMAT_YYYY_MM_DD, 2));
        System.out.println(DateUtils.getNowMillisTimestamp());
        DateUtils.formatTimeBySeconds(3600);
        System.out.println("当前秒级的时间戳：" + DateUtils.getNowSecondTimestamp());
        System.out.println("时间差：" + DateUtils.getDiffSecondOfDateTime("2021-11-02 10:55:30", "2021-11-02 10:54:00", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS));
        System.out.println("时间合法性：" + DateUtils.validateDate("2022-1-1", DateUtils.FORMAT_YYYY_MM_DD));
        System.out.println("时间字符串格式化：" + DateUtils.getDateStr("2022-01-12 11:01:30", DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS, DateUtils.FORMAT_YYYY));
        System.out.println("前几天的时间：" + DateUtils.getAfterDayDateStr(DateUtils.FORMAT_YYYY_MM_DD, -14));
        System.out.println("两个时间的比较：" + DateUtils.compareTwoDateTime("2022-03-24", "2022-03-23", DateUtils.FORMAT_YYYY_MM_DD));
        System.out.println("格式化sql时间戳：" + DateUtils.sqlTimestampToStr(new Timestamp(System.currentTimeMillis()), DateUtils.FORMAT_YYYY_MM_DD_HH_MI_SS));
    }

}
