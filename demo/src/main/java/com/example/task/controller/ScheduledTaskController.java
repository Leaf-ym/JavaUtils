package com.example.task.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengym
 * @version 1.0
 * @desc 定时任务控制器
 * @date 2022/10/29 14:01
 */
@Slf4j
@RestController
@RequestMapping(value = "/ncepu/scheduled")
public class ScheduledTaskController {

    //@Scheduled(cron = "0 */1 * * * ?")
    public void task1() {
        log.info("定时任务：每1分钟执行一次");
    }

    //@Scheduled(cron = "*/30 * * * * ?")
    public void task2() {
        log.info("定时任务：每30秒执行一次");
    }

    //@Scheduled(cron = "*/1 * * * * ?")
    public void task3() {
        log.info("定时任务：每1秒执行一次");
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void task4() {
        log.info("定时任务：每5分执行一次");
    }
}
