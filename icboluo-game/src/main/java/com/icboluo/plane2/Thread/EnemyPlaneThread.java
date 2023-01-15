package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.EnemyPlane;
import com.icboluo.util.RandomHelper;
import com.icboluo.util.ThreadUtil;

import static com.icboluo.plane2.AtkAll.enemyPlanes;
import static com.icboluo.plane2.AtkAll.player;

/**
 * 敌方飞机线程
 *
 * @author icboluo
 */
public class EnemyPlaneThread implements Runnable {

    /**
     * 不断地在上方产生敌方飞机，并给每一个飞机创建发射子弹的线程
     */
    @Override
    public void run() {
        int sleepTime = 800;
        while (player.isAlive()) {
            if (player.score >= 500) {
                sleepTime = 300;
            }
            EnemyPlane enemyPlane = new EnemyPlane();
            enemyPlanes.add(enemyPlane);
            new Thread(new EnemyBulletThread(enemyPlane)).start();
            ThreadUtil.sleep(sleepTime + RandomHelper.nextInt(300));
        }
    }
}
