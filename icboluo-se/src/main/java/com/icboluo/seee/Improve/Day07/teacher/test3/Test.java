package com.icboluo.seee.Improve.Day07.teacher.test3;

 class Test {
    public static void main(String[] args) {

        // 1. 创建一个数据共享对象
        Phone phone = new Phone();

        // 2. 创建一个生产任务
        FuShiKangFactory fuShiKangFactory = new FuShiKangFactory(phone);

        // 3. 创建一个消费任务
        ChineseConsumer chineseConsumer = new ChineseConsumer(phone);

        // 4. 创建线程对象, 让线程对象与 `生产 / 消费` 任务关联
        Thread t1 = new Thread(fuShiKangFactory, "生产线程");
        Thread t2 = new Thread(chineseConsumer, "消费线程");

        // 5. 启动线程
        t1.start();
        t2.start();
    }
}
