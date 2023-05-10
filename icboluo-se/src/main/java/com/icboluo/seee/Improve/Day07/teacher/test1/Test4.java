package com.icboluo.seee.Improve.Day07.teacher.test1;

public class Test4 {
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
                            lock.wait(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " 自动苏醒了...");
                    }
                }
            }
        }, "计时等待线程").start();
    }
}
