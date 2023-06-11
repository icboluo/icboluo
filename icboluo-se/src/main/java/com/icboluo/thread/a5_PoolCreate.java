package com.icboluo.thread;

import java.util.concurrent.*;

/**
 * @author icboluo
 * @since 2022-05-21 13:26
 */
public class a5_PoolCreate {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(100));
        // 无返回值
        executor.execute(new a2_Run());
        // 有返回值
        Future<Integer> submit = executor.submit(new a1_Call());
        // 线程状态存储在Future中
        System.out.println(submit.isDone());
        executor.shutdown();
    }
}
