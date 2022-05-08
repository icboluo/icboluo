package com.icboluo.async;

import com.icboluo.interceptor.UserContext;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

/**
 * @author icboluo
 * @since 2022-02-26 19:43
 */
@Slf4j
public class AsyncTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(@NonNull Runnable runnable) {
        // 禁止同步写法，会使任务进行同步处理而非异步处理
/*        try {
            UserContext.set("");
            runnable.run();
        } catch (Exception e) {
            log.error("async task decorator run fail,msg is", e);
        } finally {
            UserContext.remove();
        }*/

        try {
            // 部分操作
            return () -> {
                try {
                    UserContext.set("");
                    // 使用MDC也是可以的
                    // MDC.setContextMap(new HashMap<>());
                    runnable.run();
                } catch (Exception e) {
                    log.error("async task decorator run fail,msg is", e);
                } finally {
                    UserContext.remove();
                    // MDC.clear();
                }
            };
        } catch (Exception e) {
            // 打印部分操作的异常信息
            return runnable;
        }
    }
}
