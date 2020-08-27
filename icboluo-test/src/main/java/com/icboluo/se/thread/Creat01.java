package com.icboluo.se.thread;

/**
 * 继承Thread类创建线程类
 * 定义Thread类的子类，并重写该类的run方法，该run方法的方法体就代表了线程要完成的任务。因此把run()方法称为执行体
 *
 * @author icboluo
 * @date 2020-08-14 10:43
 */
public class Creat01 extends Thread {

    /**
     * 编写的是任务类代码 exa:上传，下载，发送......
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程1正在执行" + i);
        }
    }
}
