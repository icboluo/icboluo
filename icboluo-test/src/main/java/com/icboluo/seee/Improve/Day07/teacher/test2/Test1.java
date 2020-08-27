package com.icboluo.seee.Improve.Day07.teacher.test2;

public class Test1 {
    public static void main(String[] args) {

        // 1. 创建一个 `共享数据` 对象
        BaoZi baoZi = new BaoZi();

        // 2. 创建一个生产线程对象
        ProduceThread produceThread = new ProduceThread(baoZi);
        produceThread.setName("生产线程");

        // 3. 创建一个消费线程对象
        ConsumeThread consumeThread = new ConsumeThread(baoZi);
        consumeThread.setName("消费线程");

        // 4. 启动生产和消费线程
        produceThread.start();
        consumeThread.start();
    }
}
