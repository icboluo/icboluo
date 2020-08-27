package com.icboluo.seee.Improve.Day07.Test04;

 class Consume implements Runnable {
    private Phone phone;

    //构造方法
    public Consume(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (phone) {
                consumePhone();
            }
        }
    }

    public void consumePhone() {
        try {
            phone.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费了"+phone.brand+phone.price+"的手机");
        phone.price = 0.00f;
        phone.brand = null;

        phone.flag=false;
        phone.notify();
    }
}
