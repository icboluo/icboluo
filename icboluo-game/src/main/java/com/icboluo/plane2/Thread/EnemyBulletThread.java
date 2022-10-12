package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.EnemyBullet;
import com.icboluo.plane2.BaseClass.EnemyPlane;
import com.icboluo.plane2.MyDataStructrue.MyArrayList;
import com.icboluo.util.ThreadUtil;

import java.util.Random;

/**
 * 敌方子弹线程
 *
 * @author icboluo
 */
public class EnemyBulletThread implements Runnable {
    public volatile static MyArrayList<EnemyBullet> enemyBullets = new MyArrayList<>();
    private EnemyPlane enemyPlane;
    private Random random = new Random();

    public EnemyBulletThread(EnemyPlane enemyPlane) {
        this.enemyPlane = enemyPlane;
    }

    /**
     * @author liTianLu
     * @Date 2022/5/17 23:21
     * @purpose 敌方飞机存在的时候，不定时发射子弹
     */
    @Override
    public void run() {
        ThreadUtil.sleep(2000);
        while (enemyPlane.isAlive()) {
            int enemyBullet_x = enemyPlane.getX() + 25;
            int enemyBullet_y = enemyPlane.getY() + 66;
            EnemyBullet enemyBullet = new EnemyBullet(enemyBullet_x, enemyBullet_y);

            enemyBullets.add(enemyBullet);
            ThreadUtil.sleep(2000 + random.nextInt(2000));
        }
    }
}
