package com.icboluo.util;

import com.icboluo.interceptor.WebContext;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author icboluo
 * @since 2022-02-26 19:43
 */
@Slf4j
@AllArgsConstructor
public class ThreadTaskDecorator implements TaskDecorator {

    private String threadNamePre;

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
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        AtomicReference<String> methodName = new AtomicReference<>("");
        Arrays.stream(stackTrace)
                .filter(st -> st.getClassName().contains("com.icboluo"))
                .filter(st -> !st.getClassName().contains("com.icboluo.util.ThreadTaskDecorator"))
                .findFirst()
                .ifPresent(st -> methodName.set(st.getMethodName()));
        // 如果父线程是一个http请求，将父线程中的request传递到子线程，支持request的各种操作
        // request 是伴随着web的，子线程因为是从主线程分裂出来的，所以不存在web请求，需要在分裂的过程中增加一个 request设置
        RequestContextHolder.setRequestAttributes(RequestContextHolder.getRequestAttributes(), true);
        try {
            return () -> {
                String preName = Thread.currentThread().getName();
                try {
                    Thread.currentThread().setName(threadNamePre + methodName + preName);
                    // 使用MDC也是可以的
                    MDC.setContextMap(new HashMap<>());
                    runnable.run();
                } catch (Exception e) {
                    log.error("async task decorator run fail,msg is", e);
                } finally {
                    WebContext.remove();
                    MDC.clear();
                    Thread.currentThread().setName(preName);
                }
            };
        } catch (Exception e) {
            // 打印部分操作的异常信息
            return runnable;
        }
    }
}
