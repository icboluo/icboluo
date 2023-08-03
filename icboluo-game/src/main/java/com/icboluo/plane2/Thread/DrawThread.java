package com.icboluo.plane2.Thread;

import com.icboluo.plane2.BaseClass.BackGround;
import com.icboluo.plane2.BaseClass.MyPlane;
import com.icboluo.plane2.GameBusiness;
import com.icboluo.plane2.PlanConstant;
import com.icboluo.util.SimpleThreadUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

import static com.icboluo.plane2.AtkAll.*;


/**
 * 图层线程
 *
 * @author icboluo
 */
public class DrawThread implements Runnable {

    String myPlaneFileName = PlanConstant.GAME2 + "img_plane_cat.png";
    BufferedImage bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);


    @Override
    public void run() {
        Graphics buffg = bufferedImage.getGraphics();
        BackGround backGround = new BackGround(buffg);
        myPlane = new MyPlane(buffg, myPlaneFileName);

        CompletableFuture.runAsync(myPlane);
        int m = 0;
        while (player.isAlive()) {
            // 画背景
            backGround.draw(m, player.mapNum);
            m = m + 2;
            if (m >= backGround.getH()) {
                m = 0;
            }
            // 战机
            myPlane.draw(m);
            player.drawScore(buffg);
            player.drawHp(buffg);
            // 我的子弹
            for (int i = 0; i < MyPlane.bulletList.size(); i++) {
                MyPlane.bulletList.get(i).draw(buffg);
            }
            // 敌方飞机
            for (int i = 0; i < enemyPlanes.size(); i++) {
                enemyPlanes.get(i).draw(buffg);
            }
            // 敌方子弹
            for (int i = 0; i < enemyBullets.size(); i++) {
                enemyBullets.get(i).draw(buffg);
            }
            // 道具
            for (int i = 0; i < myPropList.size(); i++) {
                myPropList.get(i).draw(buffg);
            }

            GameBusiness.graphics.drawImage(bufferedImage, 0, 0, null);
            SimpleThreadUtil.sleep(30);
        }
    }
}
