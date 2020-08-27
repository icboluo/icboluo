package com.icboluo.seee.Improve.Day07.teacher.test1;

public class Test1 {
    public static void main(String[] args) {

        // 一 : 匿名子类
        Object obj1 = new Object();      // obj1 是一个 Object 类型的对象.
        Object obj2 = new Object(){};    // obj2 是一个 Object 类型的子类对象. (匿名子类)

        Thread t1 = new Thread();       // t1 是一个 Thread 类对象.
        Thread t2 = new Thread(){};     // t2 是一个 Thread 类的子类对象. (匿名子类)

        // 二 : 匿名实现类
        // Runnable 接口 : run() 抽象方法. run 方法是子线程执行的入口
        // Runnable task1 = new Runnable();        // 接口不能实例化对象, 因为接口中没有抽象方法的实现体.

        // Runnable task2 = new Runnable(){ 提供抽象方法的实现体. }  这个对象就是 Runnable 接口的实现类对象.
        // new Thread(Runnable target, String name);
    }
}
