package com.ncepu.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc 常量类
 * @date 2022/4/29 14:48
 */
public class Constants {

    /**************** 常用字符串 **********************/
    public static final String COMMON_STR_EMPTY = "";
    public static final String COMMON_STR_ZERO = "0";
    public static final String COMMON_STR_ONE = "1";
    public static final String COMMON_STR_TWO = "2";
    public static final String COMMON_STR_THREE = "3";
    public static final String COMMON_STR_FOUR = "4";
    public static final String COMMON_STR_NEGATIVE_ONE = "-1";
    public static final String COMMON_STR_NEGATIVE_TWO = "-2";
    public static final String COMMON_STR_N = "N";
    public static final String COMMON_STR_X = "X";
    public static final String COMMON_STR_Y = "Y";
    public static final String COMMON_STR_W = "W";
    /**************** 常用字符串 *********************/

    /**************** 常用数字 ***********************/
    public static final Integer COMMON_INT_ZERO = 0;
    public static final Integer COMMON_INT_ONE = 1;
    public static final Integer COMMON_INT_TWO = 2;
    public static final Integer COMMON_INT_THREE = 3;
    public static final Double COMMON_DOUBLE_ZERO = 0.0;
    public static final Double COMMON_DOUBLE_ONE = 1.0;
    public static final Double COMMON_DOUBLE_TWO = 2.0;
    public static final Double COMMON_DOUBLE_THREE = 3.0;
    /**************** 常用数字 ***********************/

    /**************** 常用标点符号 ********************/
    public static final String COMMON_MARK_SQM = "'";
    /**
     * 英文逗号
     */
    public static final String COMMON_MARK_COMMA = ",";
    public static final String COMMON_MARK_QUESTION = "?";
    public static final String COMMON_MARK_EXCLAMATORY = "!";
    /**
     * 反斜杠
     */
    public static final String COMMON_MARK_BACKSLASH = "/";
    /**
     * 斜杠
     */
    public static final String COMMON_MARK_SLASH = "\\";
    /**************** 常用标点符号 ********************/

    /**************** 加密相关字符串 ******************/
    public static final String PUT = "PUT";
    public static final String OUT = "OUT";
    public static final List<String> INCLUDE_USER_INFO = Arrays.asList("cellPhone", "idCard", "cardType");
    /**************** 加密相关字符串 ******************/
}
