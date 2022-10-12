package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.MyPlane;
import com.icboluo.util.ThreadUtil;

/**
 * @author icboluo
 */
public class MoveThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            // 我的子弹
            synchronized (MyPlane.myBulletList) {
                if (MyPlane.myBulletList.size() != 0) {
                    for (int i = 0; i < MyPlane.myBulletList.size(); i++) {
                        MyPlane.myBulletList.get(i).setY(MyPlane.myBulletList.get(i).getY() + MyPlane.myBulletList.get(i).getSpeedY());

                        if (MyPlane.myBulletList.get(i).getY() <= -100) {
                            MyPlane.myBulletList.remove(i);
                            continue;
                        }
                    }
                }
            }

            // 道具移动
            if (TestCrashThread.myPropList.size() != 0) {
                for (int i = 0; i < TestCrashThread.myPropList.size(); i++) {
                    TestCrashThread.myPropList.get(i).setY(TestCrashThread.myPropList.get(i).getY() + TestCrashThread.myPropList.get(i).getSpeedY());
                    if (TestCrashThread.myPropList.get(i).getY() >= 800) {
                        TestCrashThread.myPropList.remove(i);
                        continue;
                    }
                }
            }

            // 敌机移动
            if (EnemyPlaneThread.enemyPlanes.size() != 0) {
                for (int i = 0; i < EnemyPlaneThread.enemyPlanes.size(); i++) {
                    EnemyPlaneThread.enemyPlanes.get(i).move();
                    if (EnemyPlaneThread.enemyPlanes.get(i).getY() >= 800) {
                        EnemyPlaneThread.enemyPlanes.get(i).setAlive(false);
                        EnemyPlaneThread.enemyPlanes.remove(i);
                        continue;
                    }
                }
            }

            // 敌方子弹移动
            if (EnemyBulletThread.enemyBullets.size() != 0) {
                for (int i = 0; i < EnemyBulletThread.enemyBullets.size(); i++) {
                    EnemyBulletThread.enemyBullets.get(i).move();

                    if (EnemyBulletThread.enemyBullets.get(i).getY() >= 800) {
                        EnemyBulletThread.enemyBullets.remove(i);
                    }
                }
            }
            // 我的飞机移动
            if (DrawThread.myPlane != null) {
                DrawThread.myPlane.moveX();
                DrawThread.myPlane.moveY();
            }

            ThreadUtil.sleep(30);
        }
    }
}
