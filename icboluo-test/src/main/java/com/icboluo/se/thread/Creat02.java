package com.icboluo.se.thread;

/**
 * @author icboluo
 * @date 2020-08-14 10:49
 */
public class Creat02 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}
