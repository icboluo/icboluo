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

    /**
     * I/O 密集型任务的线程池
     *
     * @return 线程池任务执行器
     */
    @Bean
    public ThreadPoolTaskExecutor ioExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int cpu = Runtime.getRuntime().availableProcessors();
        int coreSize = Math.max(10, cpu * 2);
        executor.setCorePoolSize(coreSize);
        // 这个最大线程设置了意义不大啊，基本不会达到
        executor.setMaxPoolSize(coreSize * 2);
        executor.setQueueCapacity(coreSize * 100);
        executor.setThreadNamePrefix("-");
        executor.setTaskDecorator(new ThreadTaskDecorator("ioAsync-"));
        executor.initialize();
        return executor;
    }

    @Bean
    public ThreadPoolTaskExecutor cpuExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int cpu = Runtime.getRuntime().availableProcessors();
        int coreSize = Math.max(10, cpu + 1);
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(coreSize * 2);
        executor.setQueueCapacity(coreSize * 100);
        executor.setThreadNamePrefix("-");
        executor.setTaskDecorator(new ThreadTaskDecorator("ioAsync-"));
        executor.initialize();
        return executor;
    }
}
