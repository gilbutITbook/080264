package com.springtour.example.chapter12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncEventConfig {

    // 비동기로 Event 를 처리하려면 다음 @Bean 주석을 제거하고 실행합니다.
    // @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster applicationEventMulticaster(TaskExecutor asyncEventTaskExecutor) {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(asyncEventTaskExecutor);
        return eventMulticaster;
    }

    @Bean
    public TaskExecutor asyncEventTaskExecutor() {
        ThreadPoolTaskExecutor asyncEventTaskExecutor = new ThreadPoolTaskExecutor();
        asyncEventTaskExecutor.setMaxPoolSize(10);
        asyncEventTaskExecutor.setThreadNamePrefix("eventExecutor-");
        asyncEventTaskExecutor.afterPropertiesSet();
        return asyncEventTaskExecutor;
    }


}
