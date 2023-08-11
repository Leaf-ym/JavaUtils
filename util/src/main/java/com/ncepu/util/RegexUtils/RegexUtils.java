package com.ncepu.util.RegexUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wengym
 * @version 1.0
 * @desc 正则表达式工具类
 * @date 2023/7/27 9:06
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
     * @date 2023/7/27 9:15
     *
     * @return java.lang.Boolean
     */
    public static Boolean isMatch(String str, String regex) {
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建指定字符串的匹配器
        Matcher matcher = pattern.matcher(str);
        // 匹配结果
        Boolean isMatch = matcher.matches();
        return isMatch;
    }
}
