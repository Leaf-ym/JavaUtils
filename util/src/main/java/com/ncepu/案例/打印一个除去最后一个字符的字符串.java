package com.ncepu.案例;

import com.ncepu.util.PrintUtils;

/**
 * @author wengym
 * @version 1.0
 * @desc TODO
 * @date 2022/2/24 15:58
 */
public class 打印一个除去最后一个字符的字符串 {

    public static void main(String[] args) {
        String cnaNumber = "M0511000001M";
        PrintUtils.println(cnaNumber.substring(0, cnaNumber.length()-1));
    }
}
