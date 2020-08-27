package com.icboluo.se.thread;

import org.junit.Test;

/**
 * 线程池的5种状态：
 * new出来 创建状态
 * 调用start方法 就绪状态
 * 调成当前线程（也就是说cpu执行本线程里面的东西） 运行状态
 * 线程运行的过程被暂停（sleep,suspend，wait等方法都可以导致线程阻塞） 阻塞状态
 * run方法执行完或者调用stop方法 线程死亡，无法用start方法就绪  死亡状态
 *
 * @author icboluo
 * @date 2020-08-14 10:42
 */
public class CreateThreadDemo {
    public static void main(String[] args) {
        //        创建Thread子类的实例，即创建了线程对象
        Creat01 creat01 = new Creat01();
//        使用线程对象调用 start() 方法, 启动子线程.（子线程一旦启动，就会执行run方法，因为子线程入口是run方法）
//        start;调用操作系统底层资源，为当前程序开辟一条新执行路径(子线程)
//        如果直接调用run方法，就相当于调用了一个普通的方法，必须等run方法执行完之后再执行其他代码，
//        start方法是真正的开辟了一个线程
        creat01.start();

//        创建 Runnable实现类的实例，并依此实例作为Thread的target来创建Thread对象，该Thread对象才是真正的线程对象。
        Creat02 creat02 = new Creat02();
        Thread thread = new Thread(creat02);
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程" + i);
        }
    }
}
