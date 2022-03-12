package com.icboluo.async;

import com.icboluo.interceptor.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

/**
 * @author icboluo
 * @date 2022-02-26 19:43
 */
@Slf4j
public class AsyncTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        try {
            UserContext.set("");
            runnable.run();
        } catch (Exception e) {
            log.error("async task decorator run fail,msg is", e);
        } finally {
            UserContext.remove();
        }
        return runnable;
    }

}
