package com.icboluo.thread;

import com.icboluo.util.SimpleThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author icboluo
 * @since 2025-09-06 19:16
 */
class Thread_Safe {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            print();
        }
    }

//    为什么没有null呢
    public static void print() {
//        多线程环境下使用不安全列表
        List<Integer> list = new ArrayList<>();
//        List<Integer> list = new CopyOnWriteArrayList<>();
        List<CompletableFuture<?>> cfList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                SimpleThreadUtil.sleep(100);
                list.add(finalI);
            });
            cfList.add(cf);
        }
        CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0])).join();
        System.out.println(list);
    }
}
