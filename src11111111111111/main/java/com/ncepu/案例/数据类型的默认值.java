package com.ncepu.案例;

/**
 * @author wengym
 * @version 1.0
 * @desc 数据类型的默认值
 * @date 2022/3/17 15:09
 */
public class 数据类型的默认值 {
    short ss;
    int i;
    long l;
    boolean b;
    float f;
    double d;
    char c;
    String s;
    Object o;
    int[] ints;

    Short sss;
    Integer ii;
    Long ll;
    Boolean bb;
    Float ff;
    Double dd;
    Character cc;

    public void printAll() {
        System.out.println("Java各数据类型的初始值如下\n" +
                "short:" + ss + "\n" +
                "int:" + i + "\n" +
                "long:" + l + "\n" +
                "boolean:" + b + "\n" +
                "float:" + f + "\n" +
                "double:" + d + "\n" +
                "char:" + c + "\n" +
                "String:" + s + "\n" +
                "Object:" + o + "\n" +
                "Array:" + ints + "\n"
        );
    }

    public void printAllObject() {
        System.out.println("Java各数据类型的初始值如下\n" +
                "Short:" + sss + "\n" +
                "Int:" + ii + "\n" +
                "Long:" + ll + "\n" +
                "Boolean:" + bb + "\n" +
                "Float:" + ff + "\n" +
                "Double:" + dd + "\n" +
                "Character:" + cc + "\n"
        );
    }

    public static void main(String[] args) {
        数据类型的默认值 a = new 数据类型的默认值();
        a.printAll();
        a.printAllObject();
    }
}
