package com.icboluo.plane2.BaseClass;

import com.icboluo.plane2.PlanConstant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyBullet extends FlyObject {

    BufferedImage bufferedImage;

    public MyBullet(int x, int y, int speedY) throws IOException {
        this.speedY = speedY;

        bufferedImage = ImageIO.read(new File(PlanConstant.GAME2 + "bullet_fire.png"));
        this.x = x;
        this.y = y;
        sizeX = 28;
        sizeY = 44;
    }

    public void draw(Graphics g) {
        g.drawImage(bufferedImage, x, y, x + 28, y + 44, 0, 0, 28, 44, null);
    }
}
