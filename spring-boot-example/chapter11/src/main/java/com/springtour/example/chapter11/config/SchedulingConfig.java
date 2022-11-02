package com.springtour.example.chapter11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@EnableScheduling
@Configuration
public class SchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("TaskScheduler-");
        taskScheduler.initialize();

        taskRegistrar.setTaskScheduler(taskScheduler);
    }

    //    @Bean
    //    public TaskScheduler taskScheduler() {
    //        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    //        taskScheduler.setPoolSize(10);
    //        taskScheduler.setThreadNamePrefix("TaskScheduler-Bean-");
    //        taskScheduler.initialize();
    //        return taskScheduler;
    //    }
}
