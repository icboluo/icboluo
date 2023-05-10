package com.icboluo.seee.Improve.Day07.Test05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
线程池使用回收线程的方式，多用在多而短的线程中
 */
 class Test {
    public static void main(String[] args) {
       //ExecutorService e = Executors.newCachedThreadPool();好多个线程
         ExecutorService e = Executors.newFixedThreadPool(3);//固定线程
        //ExecutorService e = Executors.newSingleThreadExecutor();1个线程
        for (int i = 0; i < 50; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    int sum = 0;
                    for (int j = 0; j < 100; j++) {
                        sum += j;
                    }
                    System.out.println(sum);
                }
            };

            e.submit(task);//提交
//        e.shutdown();//关机关门  关闭
        }
    }
}