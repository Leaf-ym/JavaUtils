package com.ncepu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wengym
 * @version 1.0
 * @desc TODO
 * @date 2022/1/19 13:54
 */
public class ThreadUtils {
    public static void testCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<Future<Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<Object> future = threadPool.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    countDownLatch.countDown();
                    System.out.println("执行线程:"+countDownLatch.getCount());
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
