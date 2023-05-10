package com.icboluo.seee.Improve.Day07.teacher.test1;

public class Test3 {
    public static void main(String[] args) {

        // 匿名实现类 :
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 死循环
                while (true) {
                    try {
                        Thread.sleep(1000);  // 当前线程如果执行到 sleep 方法, 就会进入到 `计时等待` 状态.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 自动苏醒了...");
                }
            }
        }, "计时等待线程").start();

    }
}
