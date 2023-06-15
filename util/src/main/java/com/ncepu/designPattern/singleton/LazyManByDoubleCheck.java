package com.ncepu.designPattern.singleton;

/**
 * @author wengym
 * @version 1.0
 * @desc 双重检查的懒汉式单例：为了避免内存空间浪费，采用懒汉式单例，即用到该单例对象的时候再创建。
 * @date 2023/4/27 9:11
 */
public class LazyManByDoubleCheck {
    private LazyManByDoubleCheck() {
        System.out.println(Thread.currentThread().getName());
    }

    ;

    public static LazyManByDoubleCheck lazyMan;

    public static LazyManByDoubleCheck getInstance() {
        // 第一重检查，检查是否有引用指向对象，高并发情况下会有多个线程同时进入
        if (lazyMan == null) {
            // 同步锁锁，保证只有一个线程进入
            synchronized (LazyManByDoubleCheck.class) {
                // 双重检查，防止多个线程同时进入第一层检查(因单例模式只允许存在一个对象，故在创建对象之前因为没有引用指向对象，所有线程均可进入第一层检查)
                // 当某一线程获得锁创建一个LazyMan对象时,即已有引用指向对象，lazyMan不为空，从而保证只会创建一个对象
                // 假设没有第二层检查，那么第一个线程创建完对象释放锁后，后面进入对象也会创建对象，会产生多个对象
                // 第二重检查
                if (lazyMan == null) {
                    // synchronized关键字作用为禁止指令重排，保证返回Singleton对象一定在创建对象后
                    lazyMan = new LazyManByDoubleCheck();
                    // 这行代码存在的问题，不能保证原子性，实际上会执行以下操作：
                    // (1)在堆上开辟空间，(2)属性初始化，(3)引用指向对象
                    // 假设以上三个操作为三条单独指令，因指令重排可能会导致执行顺序为1->3->2(正常为1->2->3),当单例模式中存在普通变量需要在构造方法中进行初始化操作时，
                    // 单线程情况下，顺序重排没有影响；但在多线程情况下，假如线程1执行lazyMan = new LazyMan()语句时先1再3，由于系统调度线程2的原因没来得及执行步骤2，
                    // 但此时已有引用指向对象也就是lazyMan!=null，故线程2在第一次检查时不满足条件直接返回lazyMan，此时lazyMan为null
                    // synchronized关键字可保证lazyMan = new LazyMan()语句执行顺序为123，因其为非原子性依旧可能存在系统调度问题(即执行步骤时被打断)，
                    // 但能确保的是只要lazyMan!=0，就表明一定执行了属性初始化操作；而若在步骤3之前被打断，此时lazyMan依旧为null，其他线程可进入第一层检查向下执行创建对象。
                }
            }
        }
        return lazyMan;
    }
}