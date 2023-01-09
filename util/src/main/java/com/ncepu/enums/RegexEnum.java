package com.ncepu.enums;

/**
 * @author wengym
 * @version 1.0
 * @desc 正则表达式枚举类
 * @date 2022/7/15 9:03
 */
public enum RegexEnum {
    /**
     * 多个空格
     */
    MULTIPLE_SPACE("多个空格", "\\s+"),
    /**
     * ^：匹配字符串的开始位置
     * $：匹配字符串的结束位置
     * *：匹配前一个字符0次或n次
     * +：匹配前一个字符1次或n次
     * {n, m}：匹配前一个字符至少n次，且最多匹配m次
     * x|y：匹配x或y
     * [a-z]：表示某个范围内的字符。与指定区间内的任何字符匹配。
     */
    EMAIL("电子邮箱", "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    private String label;
    private String value;

    RegexEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
