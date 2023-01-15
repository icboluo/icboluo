package com.icboluo.plane2.BaseClass;

import com.icboluo.plane2.PlanConstant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackGround {
    Graphics g;
    BufferedImage bufferedImage_0;
    BufferedImage bufferedImage_1;
    BufferedImage bufferedImage_2;
    BufferedImage bufferedImage_3;
    BufferedImage bufferedImage_4;
    int w;
    int h;

    public BackGround(Graphics g) {

        this.g = g;
        try {
            bufferedImage_0 = ImageIO.read(new File(PlanConstant.GAME2 + "img_bg_0.jpg"));//初始背景
            bufferedImage_1 = ImageIO.read(new File(PlanConstant.GAME2 + "img_bg_1.jpg"));//地图1
            bufferedImage_2 = ImageIO.read(new File(PlanConstant.GAME2 + "img_bg_2.jpg"));//地图2
            bufferedImage_3 = ImageIO.read(new File(PlanConstant.GAME2 + "img_bg_3.jpg"));//地图3
            bufferedImage_4 = ImageIO.read(new File(PlanConstant.GAME2 + "img_bg_4.jpg"));//地图4
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = bufferedImage_1.getWidth();
        h = bufferedImage_1.getHeight();
    }

    @SuppressWarnings("all")
    public void draw(int i, int num) {
        switch (num) {
            case 1 -> {
                g.drawImage(bufferedImage_1, 0, i, w, i + h, 0, 0, w, h, null);
                g.drawImage(bufferedImage_1, 0, 0, w, i, 0, h - i, w, h, null);
            }
            case 2 -> {
                g.drawImage(bufferedImage_2, 0, i, w, i + h, 0, 0, w, h, null);
                g.drawImage(bufferedImage_2, 0, 0, w, i, 0, h - i, w, h, null);
            }
            case 3 -> {
                g.drawImage(bufferedImage_3, 0, i, w, i + h, 0, 0, w, h, null);
                g.drawImage(bufferedImage_3, 0, 0, w, i, 0, h - i, w, h, null);
            }
            case 4 -> {
                g.drawImage(bufferedImage_4, 0, i, w, i + h, 0, 0, w, h, null);
                g.drawImage(bufferedImage_4, 0, 0, w, i, 0, h - i, w, h, null);
            }
        }
    }

    public int getH() {
        return h;
    }
}
