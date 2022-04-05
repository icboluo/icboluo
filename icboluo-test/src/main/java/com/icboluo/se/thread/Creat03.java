package com.icboluo.se.thread;

import java.util.concurrent.Callable;

/**
 * ③. 通过Callable和Future创建线程
 * 创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值。
 * 创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
 * 使用FutureTask对象作为Thread对象的target创建并启动新线程。
 * 调用FutureTask对象的get()方法来获得子线程执行结束后的返回值。
 *
 * @author icboluo
 * @since 2020-08-14 13:28
 */
public class Creat03 implements Callable {
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
        return null;
    }
}
