package com.icboluo.async;

import com.icboluo.interceptor.UserContext;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.task.TaskDecorator;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author icboluo
 * @since 2022-02-26 19:43
 */
@Slf4j
public class ThreadTaskDecorator implements TaskDecorator {

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

        Locale locale = LocaleContextHolder.getLocale();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        AtomicReference<String> threadName = new AtomicReference<>("");
        Arrays.stream(stackTrace)
                .filter(st -> st.getClassName().contains("com.icboluo"))
                .filter(st -> !st.getClassName().contains("com.icboluo.async.AsyncTaskDecorator"))
                .findFirst()
                .ifPresent(st -> threadName.set(st.getMethodName()));
        try {
            // 部分操作
            return () -> {
                try {
                    Thread.currentThread().setName("async-" + threadName + Thread.currentThread().getName());
                    UserContext.set("");
                    LocaleContextHolder.setLocale(locale);
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