package com.icboluo.seee.Improve.Day07.teacher.test5;

public class Test1 {
    public static void main(String[] args) {

        // 构造方法 new Thread(Runnable target);

        // 方式一 : 实现类对象
        new Thread(new MyRunnable()).start();

        // 方式二 : 匿名实现类
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 100句
                System.out.println(Thread.currentThread().getName() + " 匿名实现类执行 ...");
            }
        }).start();

        // 方式三 : Lambda 表达式 (匿名实现类的简化写法)
        // 使用场景 : 在抽象方法的实现体代码量非常少的情况下被使用. 如果代码量非常多, 就不适合使用 Lambda 表达式.
        /*
        1. 小括号 : 抽象方法的参数列表
        2. 箭头   : 分隔符
        3. 大括号 : 抽象方法的方法体
         */
        new Thread(() -> {System.out.println(Thread.currentThread().getName() + " Lambda 类执行 ...");}).start();
    }
}


