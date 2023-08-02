package com.icboluo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
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
    public void threadFailRollBack(Runnable... runnableArr) {
        // 将任务平均分为多个组，分组的总大小不能超过线程池的大小
        List<List<Runnable>> lists = new ArrayList<>();
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
        } catch (IcBoLuoI18nException ex) {
            isException.set(true);
            hoverThread.forEach(LockSupport::unpark);
            throw ex;
        } catch (Exception ex) {
            // 最差情况，出现异常（基本上也是超时异常）之后，所有的线程均被唤醒且回滚，防止长时间占用线程或者出现事物问题
            isException.set(true);
            hoverThread.forEach(LockSupport::unpark);
            throw new IcBoLuoI18nException("", ex);
        }
    }

    /**
     * 将任务转换为多线程回滚包装的任务
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
                runList.forEach(Runnable::run);
                // 任务完成后将当前线程添加到悬停线程
                hoverThread.add(Thread.currentThread());
                // 哦安定是否需要唤醒所有子线程（唤醒后将会获得许可证，下行代码不会再有阻塞效果
                LockSupport.park();
                if (isException.get()) {
                    transactionManager.rollback(transactionStatus);
                } else {
                    transactionManager.commit(transactionStatus);
                }
            } catch (Exception exception) {

            }
        }, asyncExecutor);
    }
}
