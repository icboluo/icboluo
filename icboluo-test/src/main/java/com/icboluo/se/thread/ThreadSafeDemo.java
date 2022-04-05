package com.icboluo.se.thread;

/**
 * @author icboluo
 * @since 2020-08-14 13:34
 */
public class ThreadSafeDemo {
    public static void main(String[] args) {
        Lock01 lock01 = new Lock01();
        Thread thread1 = new Thread(lock01, "窗口1");
        Thread thread2 = new Thread(lock01, "窗口2");
        Thread thread3 = new Thread(lock01, "窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
