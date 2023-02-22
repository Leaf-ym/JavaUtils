package com.ncepu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wengym
 * @version 1.0
 * @desc 数据校验工具类
 * @date 2022/4/27 16:31
 */
public class ValidateRegUtils {

    /***
     *
     * 性别校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/3/16 17:21
     *
     * @return boolean
     *
     */
    public static boolean checkGender(String checkStr) {
        for (String str : CommonUtil.genderArray) {
            if (str.equals(checkStr)) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * 民族校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/3/16 17:21
     *
     * @return boolean
     *
     */
    public static boolean checkNationLabel(String checkStr) {
        for (String str : CommonUtil.nationArray) {
            if (str.equals(checkStr)) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * 政治面貌校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/3/16 17:21
     *
     * @return boolean
     *
     */
    public static boolean checkPoliticLabel(String checkStr) {
        for (String str : CommonUtil.politicArray) {
            if (str.equals(checkStr)) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * 证件类别校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/4/27 15:40
     *
     * @return boolean
     *
     */
    public static boolean checkCardType(String checkStr) {
        for (String str : CommonUtil.cardTypeArray) {
            if (str.equals(checkStr)) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * 学历校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/4/27 15:40
     *
     * @return boolean
     *
     */
    public static boolean checkEducationLabel(String checkStr) {
        for (String str : CommonUtil.educationArray) {
            if (str.equals(checkStr)) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * 电子邮件校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/4/27 16:33
     *
     * @return boolean
     *
     */
    public static boolean checkEmail(String checkStr) {
        String check = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(checkStr);
        return matcher.matches();
    }

    /***
     *
     * 手机号校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/4/27 16:33
     *
     * @return boolean
     *
     */
    public static boolean checkCellPhone(String checkStr) {
        String check = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(checkStr);
        return matcher.matches();
    }

    /***
     *
     * 邮政编码校验
     *
     * @param checkStr
     *
     * @author wengym
     *
     * @date 2022/4/27 16:18
     *
     * @return boolean
     *
     */
    public static boolean checkPostCode(String checkStr) {
        String check = "^\\d{6}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(checkStr);
        return matcher.matches();
    }
}
