package com.icboluo.util;

import com.icboluo.interceptor.UserContext;
import org.springframework.core.task.TaskExecutor;

/**
 * @author icboluo
 * @date 2022-02-26 19:43
 */
public class AsyncTaskDecorator implements TaskExecutor {

    @Override
    public void execute(Runnable task) {
        try {
            UserContext.set("");
//            runnable.run();
        } catch (Exception e) {

        } finally {
            UserContext.remove();
        }
    }
}
