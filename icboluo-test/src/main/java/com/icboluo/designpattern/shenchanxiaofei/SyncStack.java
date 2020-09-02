package com.icboluo.designpattern.shenchanxiaofei;

//此类是（本质上：共同访问的）共享数据区域
public class SyncStack {

    private String[] str = new String[10];

    private int index;

    //供生产者调用
    public synchronized void push(String sst) {
        if (index == sst.length()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();//唤醒在此对象监视器上等待的单个线程 
        str[index] = sst;
        index++;
    }

    //供消费者调用  
    public synchronized String pop() {
        if (index == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        String product = str[index];
        return product;

    }

    //就是定义一个返回值为数组的方法,返回的是一个String[]引用  
    public String[] pro() {
        return str;
    }
}