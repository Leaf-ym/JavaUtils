package com.ncepu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wengym
 * @version 1.0
 * @desc 正则表达式工具类
 * @date 2022/7/15 9:06
 */
public class RegexUtils {
    /**
     * 字符串是否匹配模式regex
     *
     * @param str
     *
     * @param regex
     *
     * @author wengym
     *
     * @date 2022/7/15 9:15
     *
     * @return java.lang.Boolean
     */
    public static Boolean isMatch(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        Boolean isMatch = matcher.matches();
        return isMatch;
    }
}
