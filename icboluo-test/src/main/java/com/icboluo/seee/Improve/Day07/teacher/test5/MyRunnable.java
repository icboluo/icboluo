package com.icboluo.seee.Improve.Day07.teacher.test5;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " MyRunnable 子线程被执行...");
    }
}
