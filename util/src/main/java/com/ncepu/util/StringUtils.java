package com.ncepu.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author wengym
 * @date 2021-11-2
 */
public class StringUtils {

    /**
     * UTF-8
     */
    public static final String ENCODING_UTF_8 = "UTF-8";

    /**
     * GBK
     */
    public static final String ENCODING_GBK = "GBK";

    /**
     * ISO-8859-1
     * 建设银行
     */
    public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";

    /**
     * 获取字符串特定索引处的字符
     *
     * @param str
     * @param index 大于等于0
     * @return java.lang.String
     * @author wengym
     * @date 2022/9/26 18:01
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

    /**
     * 判断字符串是否为空
     *
     * @param str
     *
     * @author wengym
     *
     * @date 2022/9/28 13:45
     *
     * @return boolean
     */
    public static boolean isNullStrTrim(Object str) {
        return str == null || "".equals(String.valueOf(str)) || "".equals(String.valueOf(str).trim()) || "null".equals(String.valueOf(str).trim());
    }

    /**
     * 处理会员号
     *
     * @param memberNum
     *
     * @param memberType
     *
     * @author wengym
     *
     * @date 2022/8/26 15:50
     *
     * @return java.lang.String
     */
    public static String dealMemberNum(String memberNum, String memberType) {
        if (CommonUtil.isNullStr(memberNum) || CommonUtil.isNullStr(memberType)) {
            return memberNum;
        }
        // 统一把字母变成大写
        String str = memberNum.toUpperCase();
        // 统一去掉有可能的后缀
        str = removeSuffix(str, "S");
        str = removeSuffix(str, "M");
        str = removeSuffix(str, "A");
        if ("S".equals(memberType)) {
            // 资深会员
            return str + "S";
        }
        if ("M".equals(memberType)) {
            // 普通会员
            return str + "M";
        }
        if ("A".equals(memberType)) {
            // 学生会员
            return str + "A";
        }
        return memberNum;
    }

    /**
     * 单纯地把数字转化为大写
     *
     * @param num
     *
     * @author wengym
     *
     * @date 2022/8/12 18:45
     *
     * @return java.lang.String
     */
    public static String numToUpper(Integer num) {
        Map<Character, String> map = new HashMap<>();
        map.put('0', "〇");
        map.put('1', "一");
        map.put('2', "二");
        map.put('3', "三");
        map.put('4', "四");
        map.put('5', "五");
        map.put('6', "六");
        map.put('7', "七");
        map.put('8', "八");
        map.put('9', "九");
        String numStr = String.valueOf(num);
        StringBuilder numSb = new StringBuilder();
        for (Character c : numStr.toCharArray()) {
            numSb.append(map.get(c));
        }
        return numSb.toString();
    }

    /***
     *
     * 去掉字符串的首尾空格，获取字符串的长度
     *
     * @param str
     *
     * @author wengym
     *
     * @date 2022/5/27 10:53
     *
     * @return int
     *
     */
    public static int getStrLength(String str) {
        if (str == null) {
            return 0;
        }
        return str.trim().length();
    }

    /***
     *
     * 字符串转字节数组
     *
     * @param str
     *
     * @param encoding
     *
     * @author wengym
     *
     * @date 2022/3/26 16:43
     *
     * @return java.lang.String
     *
     */
    public static byte[] strToByteArr(String str, String encoding) {
        byte[] arr = new byte[0];
        try {
            arr = str.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /***
     *
     * 字节数组转字符串
     *
     * @param arr
     * @param encoding
     *
     * @author wengym
     *
     * @date 2022/3/26 16:43
     *
     * @return java.lang.String
     *
     */
    public static String byteArrToStr(byte[] arr, String encoding) {
        String str = null;
        try {
            str = new String(arr, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /***
     *
     * 用占位符{}来进行字符串拼接
     *
     * @param str
     * @param strings
     *
     * @author wengym
     *
     * @date 2022/3/26 8:38
     *
     * @return java.lang.String
     *
     */
    public static String concat(String str, String... strings) {
        for (String s : strings) {
            str.replaceAll("\\{\\}", s);
        }

        return null;
    }

    /***
     *
     * 给字符串添加前缀
     *
     * @param str
     * @param prefix
     *
     * @author wengym
     *
     * @date 2022/4/29 9:28
     *
     * @return java.lang.String
     *
     */
    public static String addPrefix(String str, String prefix) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(0, prefix);
        return sb.toString();
    }

    /***
     *
     * 给字符串添加后缀
     *
     * @param str
     * @param suffix
     *
     * @author wengym
     *
     * @date 2022/4/29 9:48
     *
     * @return java.lang.String
     *
     */
    public static String addSuffix(String str, String suffix) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(sb.length(), suffix);
        return sb.toString();
    }

    /***
     *
     * 去掉字符串的前缀：如果字符串str以prefix开头，则去掉str开头处的prefix部分，并返回结果，
     * 反之，如果字符串str不以prefix开头，则直接返回str，不做处理
     *
     * @param str
     * @param prefix
     *
     * @author wengym
     *
     * @date 2022/3/11 13:58
     *
     * @return java.lang.String
     *
     */
    public static String removePrefix(String str, String prefix) {
        if (str == null || str.isEmpty() || prefix == null || prefix.isEmpty()) {
            return str;
        }
        String result = "";
        if (str.startsWith(prefix)) {
            result = str.substring(prefix.length());
        } else {
            return str;
        }
        return result;
    }

    /***
     *
     * 去掉字符串的后缀：如果字符串str以suffix结尾，则去掉str结尾处的suffix部分，并返回结果，
     * 反之，如果字符串str不以suffix结尾，则直接返回str，不做处理
     *
     * @param str
     * @param suffix
     *
     * @author wengym
     *
     * @date 2022/3/11 9:33
     *
     * @return java.lang.String
     *
     */
    public static String removeSuffix(String str, String suffix) {
        if (str == null || str.isEmpty() || suffix == null || suffix.isEmpty()) {
            return str;
        }
        String result = "";
        if (str.endsWith(suffix)) {
            result = str.substring(0, str.length() - suffix.length());
        } else {
            return str;
        }
        return result;
    }

    /***
     *
     * 去掉字符串的后缀：如果字符串str以suffix结尾，则去掉str结尾处的suffix部分，并返回结果，
     * 反之，如果字符串str不以suffix结尾，则直接返回str，不做处理
     *
     * @param str
     * @param suffix
     *
     * @author wengym
     *
     * @date 2022/3/11 9:33
     *
     * @return java.lang.String
     *
     */
    public static String removeSuffixArr(String str, String... suffix) {
        String result = str;
        for (String s : suffix) {
            result = StringUtils.removeSuffix(result, s);
        }
        return result;
    }

    /**
     * 分割字符串
     *
     * @param str
     * @param regex
     * @return
     */
    public static String[] split(String str, String regex) {
        String[] result = str.split(regex);
        return result;
    }

    /***
     *
     * @param args 连接参数数组，可以传一个数组，也可以传由逗号分隔的参数序列
     *
     * @desc 连接字符串
     *
     * @author wengym
     *
     * @date 2021/11/8 17:45
     *
     * @return java.lang.String
     *
     */
    public static String concat(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object str : args) {
            sb.append(str);
        }
        return sb.toString();
    }

    /***
     *
     * @param list 元素为基本数据类型和字符串类型的List集合（byte，char，short，int，long，boolean，float，double，String）
     *
     * @desc 把集合中的元素拼接成字符串，用特定分隔符隔开,
     *
     * @author wengym
     *
     * @date 2021/11/15 8:53
     *
     * @return java.lang.String
     *
     */
    public static <T> String join(List<T> list, String separator) {
        if (list == null || list.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T str : list) {
            sb.append(str);
            sb.append(separator);
        }
        return StringUtils.removeSuffixArr(sb.toString(), separator);
    }

    /***
     *
     * @param cardType
     *
     * @param idCard
     *
     * @desc 验证证件号码的格式合法性
     *
     * 新版（电子版）港澳通行证证件号码就是右上角C开头的数字，新版卡式证件号C开头，后面8位数字：/^C\d{8}$/
     * 旧版本式证件号W开头，将于2019年9月13日全部失效：/^W\d{8}$/
     * 新旧版本都支持的正则表达式：/^[CW]\d{8}$/
     *
     * 军官证
     * 规则： 军/兵/士/文/职/广/（其他中文） + "字第" + 4到8位字母或数字 + "号"
     * 样本： 军字第2001988号, 士字第P011816X号
     *
     * @author wengym
     *
     * @date 2021/12/28 9:28
     *
     * @return boolean
     *
     */
    public static boolean validateIdCard(String cardType, String idCard) {
        if (cardType == null || idCard == null) {
            return false;
        }
        // C13273201
        if ("港、澳、台通行证".equals(cardType)) {
            // ^[CW]\d{8}$：港澳，^[a-zA-Z][0-9]{9}$：台
            String GAReg = "^[CW]\\d{8}";
            String TWReg = "^[a-zA-Z][0-9]{9}$";
            boolean ga = Pattern.matches(GAReg, idCard);
            boolean tw = Pattern.matches(TWReg, idCard);
            return ga || tw;
        } else if ("军官证".equals(cardType)) {
            return idCard.matches("^[\\u4E00-\\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$");
        }
        return false;
    }

    /***
     * 把数字num格式化为指定长度的字符串，前面补0
     *
     * @param length 指定长度，数字长度小于指定长度的，在数字前面补0使数字长度等于指定长度，
     *               数字长度大于等于指定长度的，则不做处理，即处理后的数字长度是大于等于指定长度的
     *
     * @param  num
     *
     * @author wengym
     *
     * @date 2021/11/5 17:33
     *
     * @return java.lang.String
     *
     */
    public static String formatNum(int length, long num) {
        return String.format("%0" + length + "d", num);
    }

    /***
     *
     * @param cs1
     *
     * @param cs2
     *
     * @desc 比较两个字符串，如果两个字符串都为null也表示相等
     *
     * @author wengym
     *
     * @date 2021/11/15 9:11
     *
     * @return boolean
     *
     */
    public static boolean equals(String cs1, String cs2) {
        if (cs1 == cs2) {
            return true;
        } else if (cs1 != null && cs2 != null) {
            if (cs1.length() != cs2.length()) {
                return false;
            } else if (cs1 instanceof String && cs2 instanceof String) {
                return cs1.equals(cs2);
            } else {
                int length = cs1.length();

                for (int i = 0; i < length; ++i) {
                    if (cs1.charAt(i) != cs2.charAt(i)) {
                        return false;
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    /***
     *
     * @param str
     *
     * @desc 获取字符串的ASCII值（十进制）
     *
     * @author wengym
     *
     * @date 2021/11/15 9:14
     *
     * @return java.lang.String
     *
     */
    public static String getASCIIOfStr(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= str.length() - 1; i++) {
            char c = str.charAt(i);
            list.add(Integer.valueOf(c));
        }
        return StringUtils.join(list, ",");
    }

    /***
     *
     * @param str 需要转换的字符串
     *
     * @desc 将字符串的首字母转大写
     *
     * @author wengym
     *
     * @date 2021/11/26 18:14
     *
     * @return java.lang.String
     *
     */
    public static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /***
     *
     * @param str
     *
     * @desc 判断一个字符串是否是数字（可以判断正负，整数，小数）
     *
     * // 用ASCII码
     * for(int i=str.length();--i>=0;){
     *     int chr=str.charAt(i);
     *     if(chr<48 || chr>57)
     *     return false;
     *     }
     *
     * @author wengym
     *
     * @date 2021/12/23 14:31
     *
     * @return boolean
     *
     */
    public static boolean isNumber(String str) {
        //?：0或1个, *：0或多个, +：1或多个
        Boolean strResult = str.matches("^-?[0-9]+\\.?[0-9]*$");
        return strResult;
    }

    /***
     *
     * @param str
     *
     * @desc 判断是否是纯数字
     *
     * @author wengym
     *
     * @date 2021/12/27 17:43
     *
     * @return boolean
     *
     */
    public static boolean isPureNumber(String str) {
        //?：0或1个, *：0或多个, +：1或多个
        Boolean strResult = str.matches("^[0-9]*$");
        return strResult;
    }

    /***
     *
     * @param str
     *
     * @desc 判断一个字符串是否是整数
     *
     * for (int i = str.length();--i>=0;){
     *     if (!Character.isDigit(str.charAt(i))){
     *         return false;
     *     }
     *  }
     *
     * @author wengym
     *
     * @date 2021/12/23 14:46
     *
     * @return boolean
     *
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /***
     *
     * @param checkStr
     *
     * @desc 检验所有类型的会员号
     *
     * @author wengym
     *
     * @date 2022-02-08 10:19
     *
     * @return boolean
     *
     */
    public static boolean allCnaNumberCheck(String checkStr) {
        /*String check = "^M05[0-9a-zA-Z]*[H|S|M|A|F]$";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(checkStr);
        return matcher.matches();*/
        if (checkStr.startsWith("M05") && !checkStr.endsWith("MM")) {
            return true;
        }
        return false;
    }
}
