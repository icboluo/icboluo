package com.icboluo.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author icboluo
 * @since 2022-02-26 19:41
 */
@Configuration
public class AsyncExecutor {

    @Bean
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setTaskDecorator(new AsyncTaskDecorator());
        threadPoolTaskExecutor.setThreadNamePrefix("-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
