package com.icboluo.util;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程任务装饰器
 *
 * @author icboluo
 * @since 2022-02-26 19:43
 */
@Slf4j
@AllArgsConstructor
public class ThreadTaskDecorator implements TaskDecorator {

    private static final String INHERITABLE_THREAD_LOCALS = "inheritableThreadLocals";

    private String threadNamePre;

    @NotNull
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
        // 此块采用的是反射copy字段，还可以使用缓存提供效率
        Object parentThreadLocals = searchInheritableThreadLocals();

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        AtomicReference<String> methodName = new AtomicReference<>("");
        Arrays.stream(stackTrace)
                .filter(st -> st.getClassName().contains("com.icboluo"))
                .filter(st -> !st.getClassName().contains("com.icboluo.util.ThreadTaskDecorator"))
                .findFirst()
                .ifPresent(st -> methodName.set(st.getMethodName()));
        // 如果父线程是一个http请求，将父线程中的request传递到子线程，支持request的各种操作
        // request 是伴随着web的，子线程因为是从主线程分裂出来的，所以不存在web请求，需要在分裂的过程中增加一个 request设置
        // RequestContextHolder.setRequestAttributes(RequestContextHolder.getRequestAttributes(), true);
        try {
            return () -> {
                Thread thread = Thread.currentThread();
                String preName = thread.getName();
                thread.setName(threadNamePre + methodName + preName);
                Field field = null;
                try {
                    field = Thread.class.getDeclaredField(INHERITABLE_THREAD_LOCALS);
                    // 获取到父线程的 InheritableThreadLocals 对象
                    if (parentThreadLocals != null) {
                        field.setAccessible(true);
                        field.set(thread, parentThreadLocals);
                    }
                    // 使用MDC也是可以的
                    // MDC.setContextMap(new HashMap<>());
                    runnable.run();
                } catch (Exception e) {
                    log.error("async task decorator run fail,msg is", e);
                } finally {
                    // 这里不remove不会造成内存泄漏吗，或者数据混乱 （在线程池中，会数据混乱
//                    WebContext.remove(); // 是不是因为子线程remove之后，父线程也会被remove???
                    thread.setName(preName);
                    // 线程执行完毕之后需要执行remove方法
                    //     // 如果上面设置了field，线程执行完毕之后需要执行remove方法
                    if (parentThreadLocals != null) {
                        serFieldNull(field, thread);
                    }
                }
            };
        } catch (Exception e) {
            // 打印部分操作的异常信息
            return runnable;
        }
    }

    @SneakyThrows
    private Object searchInheritableThreadLocals() {
        Thread thread = Thread.currentThread();
        Field inheritableField = Thread.class.getDeclaredField(INHERITABLE_THREAD_LOCALS);
        inheritableField.setAccessible(true);
        // 获取到父线程的 inheritableThreadLocals 对象
        Object inheritableThreadLocals = inheritableField.get(thread);
        if (inheritableThreadLocals != null) {
            // 注意需要保证的是复制一个，而不是持有父类的引用，持有父类的话就违背了线程隔离的原则了
            // 复制的话，可以采用ThreadLocal.createInheritedMap方法，需要传入参数类型
            Class<?> threadLocalMapClazz = inheritableField.getType();
            String createInheritedMap = "createInheritedMap";
            Method createMethod = ThreadLocal.class.getDeclaredMethod(createInheritedMap, threadLocalMapClazz);
            createMethod.setAccessible(true);
            return createMethod.invoke(thread, inheritableThreadLocals);
        }
        return null;
    }

    @SneakyThrows
    private void serFieldNull(Field field, Object object) {
        if (field != null) {
            field.set(object, null);
        }
    }
}
