package com.icboluo.seee.Improve.Day07.Test04;

 class Factory implements Runnable {
    private Phone phone;

    public Factory(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (phone) {
                producePhone();
            }
        }
    }

    public void producePhone() {
        if (phone.flag==true) {
            try {
                phone.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (phone.type == false) {
            phone.brand = "IPhone";
            phone.price = 9999.99f;
        } else {

            phone.brand = "华为";
            phone.price = 666.66f;
        }
        System.out.println("生产了" + phone.brand + phone.price + "的手机");

        phone.type = !phone.type;
        phone.flag = true;

        phone.notify();
    }
}
