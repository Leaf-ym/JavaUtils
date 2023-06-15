package com.ncepu.designPattern.singleton;

/** 
 * @author wengym
 * @version 1.0
 * @desc 懒汉式单例：为了避免内存空间浪费，采用懒汉式单例，即用到该单例对象的时候再创建。
 * @date 2023/4/27 9:11
 */
public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName());
    };

    public static LazyMan lazyMan;

    public static LazyMan getInstance(){
        if (lazyMan==null){
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }
}