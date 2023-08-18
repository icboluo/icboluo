package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.EnemyBullet;
import com.icboluo.plane2.BaseClass.EnemyPlane;
import com.icboluo.util.RandomUtil;
import com.icboluo.util.SimpleThreadUtil;

import static com.icboluo.plane2.AtkAll.enemyBullets;

/**
 * 敌方子弹线程
 *
 * @author icboluo
 */
public record EnemyBulletThread(EnemyPlane enemyPlane) implements Runnable {

    /**
     * 敌方飞机存在的时候，不定时发射子弹
     */
    @Override
    public void run() {
        SimpleThreadUtil.sleep(2000);
        while (enemyPlane.isAlive()) {
            int bulletX = enemyPlane.getX() + 25;
            int bulletY = enemyPlane.getY() + 66;
            EnemyBullet enemyBullet = new EnemyBullet(bulletX, bulletY);

            enemyBullets.add(enemyBullet);
            SimpleThreadUtil.sleep(2000 + RandomUtil.nextInt(2000));
        }
    }
}
