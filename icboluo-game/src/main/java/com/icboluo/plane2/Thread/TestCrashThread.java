package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.MyPlane;
import com.icboluo.plane2.BaseClass.Prop;
import com.icboluo.plane2.MyDataStructrue.MyArrayList;
import com.icboluo.plane2.UI.GameStart;
import com.icboluo.util.ThreadUtil;

import java.io.IOException;
import java.util.Random;

public class TestCrashThread implements Runnable {
    public static MyArrayList<Prop> myPropList = new MyArrayList<>();
    private Random ran = new Random();


    @Override
    public void run() {
        int x, y;

        while (DrawThread.player.isAlive()) {
            //我方子弹与敌方飞机
            synchronized (MyPlane.myBulletList) {
                for (int i = 0; i < MyPlane.myBulletList.size(); i++) {
                    for (int j = 0; j < EnemyPlaneThread.enemyPlanes.size(); j++) {
                        if (MyPlane.myBulletList.get(i).judge_crash(EnemyPlaneThread.enemyPlanes.get(j))) {
                            EnemyPlaneThread.enemyPlanes.get(j).setAlive(false);  //关线程
                            DrawThread.player.score += 5;
                            //打中后随机掉落道具
                            if (ran.nextBoolean() && ran.nextBoolean()) {
                                x = EnemyPlaneThread.enemyPlanes.get(j).getX();
                                y = EnemyPlaneThread.enemyPlanes.get(j).getY();
                                Prop prop = null;
                                try {
                                    prop = new Prop(ran.nextInt(10));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                prop.setXY(x, y);
                                myPropList.add(prop);

                            }
                            EnemyPlaneThread.enemyPlanes.remove(j);
                            MyPlane.myBulletList.remove(i);
                            j = -1;

                        }
                        if (i >= MyPlane.myBulletList.size()) {
                            break;
                        }
                    }
                }
            }

            //我方子弹与敌方子弹
            synchronized (MyPlane.myBulletList) {
                if (EnemyBulletThread.enemyBullets.size() != 0 && MyPlane.myBulletList.size() != 0) {
                    for (int i = 0; i < MyPlane.myBulletList.size(); i++) {
                        for (int j = 0; j < EnemyBulletThread.enemyBullets.size(); j++) {
                            if (MyPlane.myBulletList.get(i).judge_crash(EnemyBulletThread.enemyBullets.get(j))) {
                                //发生碰撞
                                DrawThread.player.score += 1;
                                MyPlane.myBulletList.remove(i);   //移除
                                EnemyBulletThread.enemyBullets.remove(j);  //移除

                                j = -1;
                                if (i >= MyPlane.myBulletList.size()) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            //主角飞机与敌方子弹
            for (int j = 0; j < EnemyBulletThread.enemyBullets.size(); j++) {
                if (DrawThread.myPlane != null) {
                    if (DrawThread.myPlane.judge_crash(EnemyBulletThread.enemyBullets.get(j))) {
                        //发生碰撞
                        if (DrawThread.player.hp > 5) {
                            DrawThread.player.hp -= 5;
                            DrawThread.player.score += 1;
                        } else {
                            DrawThread.player.hp = 0;
                            DrawThread.player.setAlive(false);
                        }
                        EnemyBulletThread.enemyBullets.remove(j--);//移除

                    }
                }

            }

            //主角与敌方飞机
            for (int j = 0; j < EnemyPlaneThread.enemyPlanes.size(); j++) {
                if (DrawThread.myPlane != null) {
                    if (DrawThread.myPlane.judge_crash(EnemyPlaneThread.enemyPlanes.get(j))) {
                        //发生碰撞
                        EnemyPlaneThread.enemyPlanes.get(j).setAlive(false);
                        if (DrawThread.player.hp > 20) {
                            DrawThread.player.hp -= 20;
                            DrawThread.player.score += 5;
                        } else {
                            DrawThread.player.hp = 0;
                            DrawThread.player.setAlive(false);
                        }
                        EnemyPlaneThread.enemyPlanes.remove(j--);//移除

                    }
                }

            }

            //获取道具
            for (int j = 0; j < myPropList.size(); j++) {
                if (DrawThread.myPlane.judge_crash(myPropList.get(j))) {
                    //发生碰撞
                    if (myPropList.get(j).getType() == 0) {
                        if (DrawThread.player.hp >= 90) {
                            DrawThread.player.hp = 100;
                        } else {
                            DrawThread.player.hp += 10;
                        }
                    } else if (myPropList.get(j).getType() == 1) {
                        DrawThread.player.score += 20;
                    } else if (myPropList.get(j).getType() == 2) {
                        if (DrawThread.myPlane.getGrade() <= 2) {
                            DrawThread.myPlane.addGrade();
                        } else {
                            DrawThread.player.score += 20;
                        }

                    }

                    myPropList.remove(j);  //移除
                }
            }

            ThreadUtil.sleep(5);

            if (!DrawThread.player.isAlive()) {
                GameStart.gameOver();
            }
        }
    }
}
