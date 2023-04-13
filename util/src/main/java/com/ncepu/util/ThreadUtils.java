package com.ncepu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @author wengym
 * @version 1.0
 * @desc 线程工具类
 * @date 2022/09/20 13:54
 */
public class ThreadUtils {

    /**
     * 现在有 T1、T2、T3 三个线程，你怎样保证 T2 在 T1 执行完后执行，T3 在 T2 执行完后执行？
     *
     * @return void
     * @author wengym
     * @date 2023/4/6 9:34
     */
    public static void join() {
        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    PrintUtils.println("T1");
                }
            }
        });
        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    PrintUtils.println("T2");
                }
            }
        });
        Thread T3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    PrintUtils.println("T3");
                }
            }
        });
        try {
            T1.start();
            T1.join();
            T2.start();
            T2.join();
            T3.start();
            T3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建线程池
     * <p>
     * 线程拒绝策略
     * 如果线程池中没有空闲的线程了，且任务队列已满
     * CallerRunsPolicy：让调用这个服务的线程来处理这个任务
     * AbortPolicy：不执行新任务，直接抛出异常，提示线程池已满
     * DiscardPolicy：不执行新任务，也不抛出异常
     * DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务。
     * <p>
     * 任务阻塞队列，不初始化，默认队列大小为最大值
     *
     * @return java.util.concurrent.ExecutorService
     * @author wengym
     * @date 2022/10/10 17:33
     */
    public static ExecutorService createThreadPool() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                getNumOfProcessors() / 2,
                getNumOfProcessors(),
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        return threadPool;
    }

    /**
     * 在指定的时间后定期执行定时任务
     *
     * @param timerTask
     * @param delay     指定的延迟时间 单位是秒
     * @param period    时间间隔 单位是秒
     * @return void
     * @author wengym
     * @date 2022/9/20 14:12
     */
    public static void simpleTimer(TimerTask timerTask, long delay, long period) {
        try {
            Timer timer = new Timer();
            timer.schedule(timerTask, delay * 1000, period * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用程序运行环境的逻辑处理器数目（CPU内核数目的两倍）
     *
     * @return java.lang.Integer
     * @author wengym
     * @date 2022/10/9 9:07
     */
    public static Integer getNumOfProcessors() {
        Integer processors = Runtime.getRuntime().availableProcessors();
        return processors;
    }

    /**
     * 打印线程属性
     *
     * @return void
     * @author wengym
     * @date 2022/9/21 9:06
     */
    public static String printThreadInfo(Thread thread) {
        StringBuilder sb = new StringBuilder();
        sb.append("线程ID：" + thread.getId() + "\n");
        sb.append("线程名称：" + thread.getName() + "\n");
        sb.append("线程状态：" + thread.getState() + "\n");
        sb.append("线程优先级：" + thread.getPriority() + "\n");
        sb.append("线程是否处于活动状态：" + thread.isAlive() + "\n");
        sb.append("线程是否是守护线程：" + thread.isDaemon() + "\n");
        sb.append("线程是否是中断的：" + thread.isInterrupted() + "\n");
        return sb.toString();
    }

    public static void testCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<Future<Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<Object> future = threadPool.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    countDownLatch.countDown();
                    System.out.println("执行线程:" + countDownLatch.getCount());
                    return "result";
                }
            });
            list.add(future);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.toString());
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        ThreadUtils.testCountDownLatch();
    }
}
