package com.icboluo.seee.Improve.Day07.teacher.test3;

// 生产线程
 class FuShiKangFactory implements Runnable {
    // 属性
    private Phone phone;

    // 构造方法
    public FuShiKangFactory(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void run() {
        // 不断的生产手机
        while (true) {
            // 同步锁对象必须是 phone 对象.
            synchronized (phone) {
                producePhone();
            }

        }
    }

    // 特点 : 此处不可以使用 `同步方法`, 同步方法的锁对象是 this 对象.
    public /*synchronized*/ void producePhone() {

        // 1. 判断
        if (phone.flag == true) {
            // 有手机, 等待
            try {
                phone.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 程序来到这里, 说明当前没有手机.
        if (phone.type == false) {
            phone.brand = "iPhoneXXX";
            phone.price = 18888.88f;
        } else {
            phone.brand = "HuaWei666";
            phone.price = 666.66f;
        }

        System.out.println(Thread.currentThread().getName() + "生产了: " + phone.brand + " " + phone.price + " 手机.");

        // 3.1 修改手机类型
        phone.type = !phone.type;
        // 3.2 修改手机标记
        phone.flag = true;

        // 4. 唤醒消费线程来消费
        phone.notify();
    }
}
