package com.icboluo.seee.Improve.Day07.teacher.test1;

public class Test5 {
    public static void main(String[] args) {

        Object lock = new Object();

        // 匿名实现类 :
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 死循环
                while (true) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " 被唤醒了...");
                    }
                }
            }
        }, "死等线程").start();


        // 创建另外一条线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lock) {
                        // 唤醒在此 lock 等待的单个线程。
                        lock.notify();
                    }
                }
            }
        }, "唤醒线程").start();
    }
}
