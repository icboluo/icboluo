package com.icboluo.seee.Improve.Day07.Test03;

 class ConsumeThread extends Thread {
    private BaoZi baoZi;

    public ConsumeThread(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (baoZi) {
                //没包子，等
                if (baoZi.flag == false) {
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //有包子，消费
                System.out.println(Thread.currentThread().getName() + "正在消费" + baoZi.pier + baoZi.xianer + "包子");

                baoZi.pier = null;
                baoZi.xianer = null;
                baoZi.flag = false;

                baoZi.notify();

            }
        }
    }
}