package com.icboluo.seee.Improve.Day07.Test03;

 class ProduceThread extends Thread {
    private BaoZi baoZi;

    public ProduceThread(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (baoZi) {
                //有包子，等待
                if (baoZi.flag == true) {
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //没有，生产
                if (baoZi.Type == false) {
                    baoZi.pier = "薄皮";
                    baoZi.xianer = "猪肉馅";
                } else {
                    baoZi.pier = "厚皮";
                    baoZi.xianer = "牛肉馅";
                }

                System.out.println(Thread.currentThread().getName() + "生产了" + baoZi.pier + baoZi.xianer + "包子");
                //修正参数
                baoZi.flag = true;
                baoZi.Type = !baoZi.Type;

                //唤醒消费线程
                baoZi.notify();
            }
        }
    }
}
