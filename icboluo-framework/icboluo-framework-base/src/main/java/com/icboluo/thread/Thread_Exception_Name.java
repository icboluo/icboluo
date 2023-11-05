package com.icboluo.thread;

import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.IcBoLuoI18nException;
import com.icboluo.util.ThreadPool;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author icboluo
 * @since 2023-11-05 19:12
 */
public class Thread_Exception_Name {
    public static void main(String[] args) {
        // 如果要区分敏感异常和别的异常，可以提前全局变量存储异常msg，在()->异步函数中完成
        AtomicReference<String> errMsg = new AtomicReference<>("");
        CompletableFuture.runAsync(() -> {
            try {
                greaterThanZero(-1);
            } catch (IcBoLuoI18nException ex) {
                errMsg.set(ex.getMessage());
            }
        }).join();
        System.out.println(errMsg.get());

        CompletableFuture.runAsync(() -> greaterThanZero(-2)).handle((result, throwable) -> {
            // 没有异常就返回正常结果
            if (throwable == null) {
                return null;
            }
            // CompletableFuture handler 的异常类型全部是并发异常，判断子类型需要getCause
            if (throwable.getCause() instanceof IcBoLuoI18nException) {
                // msg信息：param.less.than.or.equal.to
                // ex.getMsg信息：com.icboluo...IcBoLuoI18nException: param.less.than.or.equal.to
                errMsg.set(throwable.getCause().getMessage());
                return null;
            } else {
                // 此块可能调用数据库产生敏感信息异常，需要丢弃
                throw new IcBoLuoException();
            }
        });
        System.out.println(errMsg.get());
    }

    public static void greaterThanZero(int a) {
        if (a <= 0) {
            throw new IcBoLuoI18nException("param.{0}.less.than.or.equal.to", new Object[]{a});
        }
    }

    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPool().asyncExecutor();

    @Test
    public void threadName() {
        // 默认线程名 ForkJoinPool.commonPool-worker-1
        // 自定义线程池的线程名：ioExecutor-threadName-1 | ioExecutor-subMethod-2
        // 如果自定义线程池不包含任何的东西，线程名是：ioExecutor-1  线程bean的名称加上序列后缀
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            subMethod();
        }, threadPoolTaskExecutor);
    }

    private void subMethod() {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, threadPoolTaskExecutor);
    }
}
