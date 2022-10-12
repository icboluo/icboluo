package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.EnemyPlane;
import com.icboluo.plane2.MyDataStructrue.MyArrayList;
import com.icboluo.util.ThreadUtil;

import java.io.IOException;
import java.util.Random;

/**
 * 敌方飞机线程
 *
 * @author icboluo
 */
public class EnemyPlaneThread implements Runnable {
    public volatile static MyArrayList<EnemyPlane> enemyPlanes = new MyArrayList<>();
    private Random random = new Random();

    /**
     * @author liTianLu
     * @Date 2022/5/17 23:21
     * @purpose 不断地在上方产生敌方飞机，并给每一个飞机创建发射子弹的线程
     */
    @Override
    public void run() {
        int sleepTime = 800;
        while (true) {
            if (DrawThread.player.score >= 500) {
                sleepTime = 300;
            }

            EnemyPlane enemyPlane = null;
            try {
                enemyPlane = new EnemyPlane();
            } catch (IOException e) {
                e.printStackTrace();
            }
            enemyPlanes.add(enemyPlane);
            new Thread(new EnemyBulletThread(enemyPlane)).start();
            ThreadUtil.sleep(sleepTime + random.nextInt(300));
        }
    }
}
