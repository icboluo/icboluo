package com.icboluo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author icboluo
 * @since 2020-08-14 10:59
 */
public class ThreadSafeDemo implements Runnable {
    private int tickets = 100;
    private final Object lock = new Object();
    Lock lock1 = new ReentrantLock();

    public static void main(String[] args) {
        ThreadSafeDemo threadSafeDemo = new ThreadSafeDemo();
        Thread thread1 = new Thread(threadSafeDemo, "窗口1");
        Thread thread2 = new Thread(threadSafeDemo, "窗口2");
        Thread thread3 = new Thread(threadSafeDemo, "窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 线程安全：多线程对共享数据同时进行增删改操作
     * 线程同步指的是，即当有一个线程在对内存进行操作时，其他线程都不可以对这个内存地址进行操作，
     * 直到该线程完成操作， 其他线程才能对该内存地址进行操作
     */
    @Override
    public void run() {
//        在while条件执行之后还没有执行ticket-- 操作，cpu切换发生重复票情况
        while (tickets > 0) {
            try {
                // sleep：让调用线程休眠，不改变锁的状态（使用synchronized的方法还是不释放锁）
                // wait：进入一个和该对象相关的等待池，释放锁，使得其他线程可以访问，可以通过notify，notifyAll方法来唤醒等待的线程
                //  notify：随机唤醒一个wait线程，notifyAll：唤醒所有等待线程
                //          被唤醒的线程会进入该对象的锁池中，锁池中的线程会竞争该对象锁
                //          优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，它还会留在锁池中，
                //          唯有线程再次调用 wait()方法，它才会重新回到等待池中。而竞争到对象锁的线程则继续往下执行，
                //          直到执行完了 synchronized 代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            如果当前票数是1，其他的线程进行了--操作，线程切换回来的时候没有再次check，出现负票
            System.out.println(Thread.currentThread().getName() + "正在售出第" + tickets-- + "张票");
        }
    }

    /**
     * 同步方法
     */
    public synchronized void run1() {
        while (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + "正在售出第" + tickets-- + "张票");
        }
    }

    /**
     * 同步代码块：类锁
     */
    public void run2() {
        while (true) {
            synchronized (ThreadSafeDemo.class) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在售出第" + tickets-- + "张票");
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 同步代码块：对象锁
     */
    public void run3() {
        while (true) {
            synchronized (lock) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在售出第" + tickets-- + "张票");
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 可重入锁
     */
    public void run4() {
        lock1.lock();
        try {
            while (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "正在售出第" + tickets-- + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }
}
