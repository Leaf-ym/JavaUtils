package com.ncepu.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wengym
 * @version 1.0
 * @desc 定时任务工具类
 * @date 2021/11/29 9:23
 */
public class MyScheduleExecutor implements Runnable {

    private Long startTime = System.currentTimeMillis() * 1000;
    private String jobName;

    public MyScheduleExecutor(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println(jobName + " is running");
        Long nowTime = System.currentTimeMillis() * 1000;
    }

    public static void main(String[] args) {
        // 创建任务队列
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10); // 10 为线程数量
        // 执行任务
        scheduledExecutorService.scheduleAtFixedRate(
                new MyScheduleExecutor("wengym"), 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次

    }
}