package com.ncepu.designPattern.singleton;

/** 
 * @author wengym
 * @version 1.0
 * @desc 饿汉式单例：类一旦加载就创建一个单例，保证在调用getInstance方法之前单例已经存在，这种饿汉式单例会造成空间浪费。
 * @date 2023/4/27 9:11
 */
public class Hungry {
    private Hungry(){}
    private final static Hungry HUNGRY = new Hungry();
    public static Hungry getInstance(){
        return HUNGRY;
    }
}
