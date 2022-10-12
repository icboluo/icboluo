package com.icboluo.plane2.BaseClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 敌人 飞机
 *
 * @author icboluo
 */
public class EnemyPlane extends FlyObject {
    private String fileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\img_plane_enemy.png";
    private BufferedImage plane_img;
    Random random = new Random();

    public EnemyPlane() throws IOException {
        plane_img = ImageIO.read(new File(fileName));
        sizeX = 90;
        sizeY = 70;
        x = random.nextInt(410);
        y = -50;
        speedX = 0;
        speedY = 1;

    }

    public void draw(Graphics g) {
        g.drawImage(plane_img, x, y, x + sizeX, y + sizeY, 0, 0, 264, 200, null);
    }
}
