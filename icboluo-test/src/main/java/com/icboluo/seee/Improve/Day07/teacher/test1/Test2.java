package com.icboluo.seee.Improve.Day07.teacher.test1;

public class Test2 {
    public static void main(String[] args) {
        /*
            查看 : 大括号, 实现体的含义.
            一 : 类如果添加大括号, 那就是匿名子类.
            二 : 接口如果添加大括号, 那就是匿名实现类.
         */

        // 一 : 匿名子类 (了解)
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " -> run ... " + i);
                }
            }
        }.start();

        // 二 : 匿名实现类
        // new Thread(Runnable target, String name);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " -> run ... " + i);
                }
            }
        }, "匿名实现类线程").start();


        // 主线程执行代码 ...
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " -> run ... " + i);
        }
    }
}
