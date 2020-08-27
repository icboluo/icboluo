package com.icboluo.seee.Improve.Day07.teacher.test3;

// 消费线程 :
 class ChineseConsumer implements Runnable {
    // 属性
    private Phone phone;

    // 构造方法
    public ChineseConsumer(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void run() {
        // 不断消费
        while (true) {

            synchronized (phone) {
                consumePhone();
            }

        }
    }

    public void consumePhone() {

        // 1. 判断是否有手机
        if (phone.flag == false) {
            try {
                phone.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 程序如果能够来到这里, 说明有手机
        System.out.println(Thread.currentThread().getName() + "正在消费: " + phone.brand + " " + phone.price + " 手机.");

        // 3. 清空数据
        phone.brand = null;
        phone.price = 0.0f;

        // 4. 修改手机的标记
        phone.flag = false;

        // 5. 唤醒生产线程来生产
        phone.notify();
    }
}
