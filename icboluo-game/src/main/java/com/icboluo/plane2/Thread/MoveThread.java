package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.*;
import com.icboluo.util.ThreadUtil;

import java.util.List;

import static com.icboluo.plane2.AtkAll.*;

/**
 * 移动线程
 *
 * @author icboluo
 */
public class MoveThread implements Runnable {

    @Override
    @SuppressWarnings("java:S3864")
    public void run() {
        while (player.isAlive()) {
            // 我的子弹
            synchronized (MyPlane.bulletList) {
                // 有一个问题，stream的顺序影响结果吗；peek操作元素，再filter结果是否一致
                List<MyBullet> unValidBullet = MyPlane.bulletList.stream()
                        .peek(FlyObject::move)
                        .filter(bullet -> bullet.getY() <= -100)
                        .toList();
                MyPlane.bulletList.removeAll(unValidBullet);
            }

            // 道具移动
            List<Prop> noValidProp = myPropList.stream()
                    .peek(FlyObject::move)
                    .filter(prop -> prop.getY() >= 800)
                    .toList();
            myPropList.removeAll(noValidProp);

            // 敌机移动
            List<EnemyPlane> noValidEnemyPlane = enemyPlanes.stream()
                    .peek(FlyObject::move)
                    .filter(prop -> prop.getY() >= 800)
                    .peek(enemyPlane -> enemyPlane.setAlive(false))
                    .toList();
            enemyPlanes.removeAll(noValidEnemyPlane);

            // 敌方子弹移动
            List<EnemyBullet> noValidEnemyBullet = enemyBullets.stream()
                    .peek(FlyObject::move)
                    .filter(prop -> prop.getY() >= 800)
                    .toList();
            enemyBullets.removeAll(noValidEnemyBullet);

            // 我的飞机移动
            if (myPlane != null) {
                myPlane.moveX();
                myPlane.moveY();
            }

            ThreadUtil.sleep(30);
        }
    }
}
