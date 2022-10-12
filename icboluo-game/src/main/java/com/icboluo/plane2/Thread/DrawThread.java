package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.BackGround;
import com.icboluo.plane2.BaseClass.MyPlane;
import com.icboluo.plane2.BaseClass.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Thread.sleep;


/**
 * @author icboluo
 */
public class DrawThread implements Runnable {

    public volatile static MyPlane myPlane = null;
    public volatile static Player player;

    static {
        try {
            player = new Player();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    Graphics g;
    Graphics buffg;
    String myPlaneFileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\img_plane_cat.png";
    BufferedImage bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);


    @Override
    public void run() {
        buffg = bufferedImage.getGraphics();
        BackGround backGround = null;
        try {
            backGround = new BackGround(buffg);
            myPlane = new MyPlane(buffg, myPlaneFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(myPlane).start();
        int m = 0;
        while (true) {
            //画背景

            backGround.draw(m, player.mapNum);
            m = m + 2;
            if (m >= backGround.getH()) {
                m = 0;
            }
            //战机
            myPlane.draw(m);
            player.drawScore(buffg);
            player.drawHp(buffg);
            //我的子弹
            for (int i = 0; i < MyPlane.myBulletList.size(); i++) {
                MyPlane.myBulletList.get(i).draw(buffg);

            }
            //敌方飞机
            for (int i = 0; i < EnemyPlaneThread.enemyPlanes.size(); i++) {
                EnemyPlaneThread.enemyPlanes.get(i).draw(buffg);
            }
            //敌方子弹
            for (int i = 0; i < EnemyBulletThread.enemyBullets.size(); i++) {
                EnemyBulletThread.enemyBullets.get(i).draw(buffg);
            }
            //道具
            for (int i = 0; i < TestCrashThread.myPropList.size(); i++) {
                TestCrashThread.myPropList.get(i).draw(buffg);
            }


            g.drawImage(bufferedImage, 0, 0, null);

            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public DrawThread(Graphics g) {
        this.g = g;
    }
}
