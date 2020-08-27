package com.icboluo.seee.Improve;

public class Test34 {
    private static Object o = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 10;
                while (i > 0) {
                    synchronized (o) {
                        Thread.currentThread().setName("上面");
                        System.out.println(Thread.currentThread().getName() + "锁开启,无限等待,2s时间到后下面启动");
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "唤醒下面程序");
                    }
                    i--;
                }
            }
        }).start();
        new Thread(new Runnable() {//匿名实现类
            @Override
            public void run() {
                int i = 10;
                while (i > 0) {
                    try {
                        System.out.println("下面等待2s，运行上面");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o) {
                        Thread.currentThread().setName("下面");
                        System.out.println(Thread.currentThread().getName()+"锁开启,运行完成后唤醒上面程序");
                        o.notify();
                    }
                    i--;
                }
            }
        }).start();
    }
}
