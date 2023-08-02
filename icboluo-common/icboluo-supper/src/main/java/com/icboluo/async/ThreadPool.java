package com.icboluo.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池
 *
 * @author icboluo
 * @see ThreadTaskDecorator
 * @since 2022-02-26 19:41
 */
@Configuration
public class ThreadPool {

    @Bean
    public TaskExecutor asyncExecutor() {
        return ioExecutor();
    }

    @Bean
    public TaskExecutor ioExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setTaskDecorator(new ThreadTaskDecorator());
        threadPoolTaskExecutor.setThreadNamePrefix("-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean
    public TaskExecutor cpuExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setTaskDecorator(new ThreadTaskDecorator());
        threadPoolTaskExecutor.setThreadNamePrefix("-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
