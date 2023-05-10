package com.icboluo.seee.Improve.Day07.teacher.test4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 {
    public static void main(String[] args) {

        // 方式一 : 无界限
        // ExecutorService threadPool = Executors.newCachedThreadPool();

        // 方式二 : 固定大小
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 方式三 : 单个线程的线程池
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 循环创建 50 个任务
        for (int i = 0; i < 50; i++) {
            Runnable task = new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int sum = 0;
                    for (int j = 1; j <= 100; j++) {
                        sum += j;
                    }
                    System.out.println(Thread.currentThread().getName() + " sum = " + sum);
                }
            };

            // 执行任务 (线程池)
            threadPool.submit(task);
        }

        // 关闭线程池
        threadPool.shutdown();
    }
}
