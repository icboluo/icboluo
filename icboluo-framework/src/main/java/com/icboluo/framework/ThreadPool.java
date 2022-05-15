package com.icboluo.framework;

import com.icboluo.util.ThreadUtil;

import java.util.concurrent.*;

/**
 * @author icboluo
 * @since 2022-05-12 22:00
 */
public class ThreadPool {
    public static void main(String[] args) {
        Run run = new Run();
        Thread thread = new Thread(run);
        thread.start();

        Call call = new Call();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(100));
        // 有返回值
        Future<Integer> submit = threadPoolExecutor.submit(call);
        // 无返回值
        threadPoolExecutor.execute(run);
        threadPoolExecutor.shutdown();
    }

    public static class Run implements Runnable {

        @Override
        public void run() {
            new ThreadUtil().sleep5s();
        }
    }

    public static class Call implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            new ThreadUtil().sleep5s();
            return 1;
        }
    }
}
