package com.icboluo.thread;

import com.icboluo.util.SimpleThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 并发异常不容忽视，在多个线程同时尝试往List增加元素使，可能出现(即便是局部变量，也要注意)
 *
 * @author icboluo
 * @since 2025-09-06 19:16
 */
class Thread_Safe {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            print();
        }
    }

    public static void print() throws InterruptedException {
        // 多线程环境下使用不安全列表
        List<Integer> list = new ArrayList<>();
        // List<Integer> list = new CopyOnWriteArrayList<>(); // 线程安全列表
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Thread.startVirtualThread(() -> {
                SimpleThreadUtil.sleep(100);
                list.add(finalI);
                latch.countDown();
            });
        }
        latch.await();  // 等待所有虚拟线程完成
        System.out.println(list);
    }
}
