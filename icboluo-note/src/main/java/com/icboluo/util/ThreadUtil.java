package com.icboluo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>多线程工具
 * <p>如何理解 Executor 此接口提供了一种将任务提交与每个任务运行方式的机制解耦的方法这句话：
 * <p>1.任务的提交是一个事件，任务的执行也是一个事件；书写方法如下：task.commit then task.executor,这个功能是在一个方法中完成的，
 * <p>我们为其提供一个 Executor的接口，耦合接口的执行分发给子类，接口的提交作为方法的参数，这样就实现了互不相干；类似于中介者模式|桥接模式
 *
 * @author icboluo
 * @since 2023-08-02 19:44
 */
@Component
@Slf4j
public class ThreadUtil {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private Executor asyncExecutor;

    /**
     * 多线程任务失败回滚
     *
     * @param runnableArr 多个任务
     */
    public void threadFailRollback(Runnable... runnableArr) {
        // 数据分组的大小，和线程池大小密切相关，不可设置过大
        List<List<Runnable>> lists = BeanUtil.groupBySize(Arrays.stream(runnableArr), 10);
        // 悬停线程||存储改未提交的数据
        List<Thread> hoverThread = Collections.synchronizedList(new ArrayList<>());
        // 所有子线程是否有发生异常的
        AtomicBoolean isException = new AtomicBoolean(false);
        AtomicInteger threadCount = new AtomicInteger(lists.size());

        CompletableFuture<Void> all = CompletableFuture.allOf(lists.stream()
                .map(runList -> runnableToCf(hoverThread, isException, threadCount, runList))
                .toArray(CompletableFuture[]::new));
        try {
            all.get(10, TimeUnit.SECONDS);
        } catch (ExecutionException ex) {
            // 这2句其实没必要，仅仅是再加一层保险
            isException.set(true);
            hoverThread.forEach(LockSupport::unpark);
            throw new I18nException("ExecutionException", ex);
        } catch (Exception ex) {
            // 最差情况，出现异常（基本上也是超时异常）之后，所有的线程均被唤醒且回滚，防止长时间占用线程或者出现事物问题
            isException.set(true);
            hoverThread.forEach(LockSupport::unpark);
            throw new I18nException("", ex);
        }
    }

    /**
     * 将任务转换为 多线程回滚包装的任务
     *
     * @param hoverThread 悬停线程
     * @param isException 多线程中是否发生异常
     * @param threadCount 总线程数
     * @param runList     当前线程
     * @return 由多线程回滚包装的任务
     */
    public CompletableFuture<Void> runnableToCf(List<Thread> hoverThread, AtomicBoolean isException, AtomicInteger threadCount, List<Runnable> runList) {
        return CompletableFuture.runAsync(() -> {
            // Spring 事物传播
            TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition(3));
            try {
                for (Runnable runnable : runList) {
                    if (!isException.get()) {
                        runnable.run();
                    }
                }
            } catch (Exception e) {
                log.error("task run fail", e);
                isException.set(true);
            }

            // 任务完成后将当前线程添加到悬停线程
            hoverThread.add(Thread.currentThread());
            // 判断是否需要唤醒所有子线程（唤醒后将会获得许可证，下行代码不会再有阻塞效果）
            if (hoverThread.size() == threadCount.get()) {
                hoverThread.forEach(LockSupport::unpark);
            }
            // 悬停当前线程
            LockSupport.park();
            if (isException.get()) {
                transactionManager.rollback(transactionStatus);
                throw new IcBoLuoException();
            } else {
                transactionManager.commit(transactionStatus);
            }
        }, asyncExecutor);
    }
}
