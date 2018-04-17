package com.ljm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Project MyWebProject
 * @ClassName SchedulingConfig
 * @Description 定时任务
 * @Author random
 * @Date Create in 2018/4/2 17:47
 * @Version 1.0
 **/
@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>SechedulingConfig.scheduler()");
    }

}
