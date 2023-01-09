package com.ncepu.案例;

/**
 * @author wengym
 * @version 1.0
 * @desc 为null的Integer变量不能赋给int变量，会报空指针异常
 * @date 2021/11/30 15:05
 */
public class 为null的Integer变量不能赋给int变量 {
    public static void main(String[] args) {
        Integer i = null;
        int j = i; // 空指针异常：java.lang.NullPointerException
    }
}
