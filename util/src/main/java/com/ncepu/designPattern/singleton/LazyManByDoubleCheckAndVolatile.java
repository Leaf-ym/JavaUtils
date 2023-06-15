package com.ncepu.designPattern.singleton;

/**
 * @author wengym
 * @version 1.0
 * @desc 双重检查和保证原子性的懒汉式单例：为了避免内存空间浪费，采用懒汉式单例，即用到该单例对象的时候再创建。
 * @date 2023/4/27 9:11
 */
public class LazyManByDoubleCheckAndVolatile {
    private LazyManByDoubleCheckAndVolatile() {
        System.out.println(Thread.currentThread().getName());
    }

    ;

    public static volatile LazyManByDoubleCheckAndVolatile lazyMan;

    public static LazyManByDoubleCheckAndVolatile getInstance() {
        // 第一重检查，检查是否有引用指向对象，高并发情况下会有多个线程同时进入
        if (lazyMan == null) {
            // 同步锁锁，保证只有一个线程进入
            synchronized (LazyManByDoubleCheckAndVolatile.class) {
                // 双重检查，防止多个线程同时进入第一层检查(因单例模式只允许存在一个对象，故在创建对象之前因为没有引用指向对象，所有线程均可进入第一层检查)
                // 当某一线程获得锁创建一个LazyMan对象时,即已有引用指向对象，lazyMan不为空，从而保证只会创建一个对象
                // 假设没有第二层检查，那么第一个线程创建完对象释放锁后，后面进入对象也会创建对象，会产生多个对象
                // 第二重检查
                if (lazyMan == null) {
                    lazyMan = new LazyManByDoubleCheckAndVolatile();
                }
            }
        }
        return lazyMan;
    }
}