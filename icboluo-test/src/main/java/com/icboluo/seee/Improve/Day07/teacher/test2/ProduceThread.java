package com.icboluo.seee.Improve.Day07.teacher.test2;

// 生产线程 : 不断生产 `数据` (数据共享类)
public class ProduceThread extends Thread {
    // 属性
    private BaoZi baoZi;

    public ProduceThread(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        // 不断生产
        while (true) {
            // 说明 : wait() / notify() 一定要在同一个同步锁环境中调用.
            synchronized (baoZi) {

                // 需求 : 生产一个 0 ~ 999 之间的随机数据.
                /*try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                // 1. 判断是否有包子
                if (baoZi.flag == true) {
                    // 有包子, 不需要生产, 等待.
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 2. 程序来到这里, 说明没有包子, 生产
                if (baoZi.type == false) {
                    baoZi.pier = "薄皮";
                    baoZi.xianer = "虾仁馅";
                } else {
                    baoZi.pier = "厚皮";
                    baoZi.xianer = "辣条馅";
                }

                // 输出查看
                System.out.println(Thread.currentThread().getName() + "生产了: " + baoZi.pier + baoZi.xianer + " 包子.");

                // 3.1 生产完毕后, 修改包子的 `类型`, 实现类型取反.
                baoZi.type = !baoZi.type;
                // 3.2 修改一下包子的标记
                baoZi.flag = true;

                // 4. 唤醒消费线程来消费
                baoZi.notify();
            }
        }
    }
}
