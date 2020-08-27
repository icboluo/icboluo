package com.icboluo.seee.Improve.Day07.Test04;

 class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();

        Factory factory = new Factory(phone);
        Consume consume = new Consume(phone);

        Thread t1 = new Thread(factory, "生产线程");
        Thread t2 = new Thread(consume, "消费线程");

        t1.start();
        t2.start();
    }
}
