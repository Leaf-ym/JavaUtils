package com.ncepu.案例;

import com.ncepu.util.PrintUtils;

/**
 * @author wengym
 * @version 1.0
 * @desc Integer类中的128陷，要避免这中陷阱，只要凡是涉及到包装类的直接用equals比较即可
 * @date 2021/11/30 15:08
 *
 * https://blog.csdn.net/qq_41644715/article/details/99981403
 */
public class 当Integer变量为128时的比较情况 {
    public static void main(String[] args) {

        Integer a = 127;
        Integer b = 128;
        Integer c = 129;
        int d = 127;
        int e = 128;
        int f = 129;
        Integer g = 127;
        Integer h = 128;
        Integer i = 129;

        System.out.println("a==d："+(a==d));
        System.out.println("b==e："+(b==e));
        System.out.println("c==f："+(c==f));

        System.out.println("a==g："+(a==g));
        System.out.println("b==h："+(b==h));
        System.out.println("c==i："+(c==i));

        PrintUtils.println("a is equals g："+(a.equals(g)));
        System.out.println("b is equals h："+(b.equals(h)));
        System.out.println("c is equals i："+(c.equals(i)));

    }
}
