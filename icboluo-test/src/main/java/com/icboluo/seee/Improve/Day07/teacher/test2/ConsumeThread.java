package com.icboluo.seee.Improve.Day07.teacher.test2;

// 消费线程 : 不断消费 `数据` (数据共享类)
public class ConsumeThread extends Thread {
    // 属性
    private BaoZi baoZi;

    // 构造方法, 接收 `共享数据` 对象
    public ConsumeThread(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        // 不断消费包子
        while (true) {

            synchronized (baoZi) {
                consume();
            }

        }
    }

    public void consume() {

        // 需求 : 生产一个 0 ~ 999 之间的随机数据.
        /*try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // 1. 判断, 如果没有包子, 不消费, 死等
        if (baoZi.flag == false) {
            try {
                baoZi.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 程序来到这里, 说明有包子, 消费
        System.out.println(Thread.currentThread().getName() + "正在消费:" + baoZi.pier + baoZi.xianer + " 包子.");

        // 3. 清空数据
        baoZi.pier = null;
        baoZi.xianer = null;

        // 4. 修改包子的标记
        baoZi.flag = false;

        // 5. 唤醒生产线程来生产
        baoZi.notify();
    }
}
