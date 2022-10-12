package com.icboluo.plane2.BaseClass;

import com.icboluo.plane2.MyDataStructrue.MyArrayList;
import com.icboluo.util.ThreadUtil;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author icboluo
 */
@Data
public class MyPlane extends FlyObject implements Runnable {
    private int grade;
    public Graphics g;
    public BufferedImage plane_img;

    public volatile static MyArrayList<MyBullet> myBulletList = new MyArrayList<>();

    public MyPlane(Graphics g, String fileName) throws IOException {
        x = 200;
        y = 600;
        speedX = 0;
        speedY = 0;
        sizeX = 141;
        sizeY = 105;
        this.g = g;
        plane_img = ImageIO.read(new File(fileName));
        grade = 1;
    }

    public void draw(int i) {
        int j = i % 30; //150ms
        if (j < 10) {
            g.drawImage(plane_img, x, y, x + sizeX, y + sizeY, 0, 0, sizeX, sizeY, null);
        } else if (j < 20) {
            g.drawImage(plane_img, x, y, x + sizeX, y + sizeY, 0, sizeY, sizeX, 2 * sizeY, null);
        } else if (j < 30) {
            g.drawImage(plane_img, x, y, x + sizeX, y + sizeY, 288, 0, 424, 112, null);
        }
    }

    public void addGrade() {
        grade++;
    }

    @Override
    public void run() {
        while (alive) {
            int bullet_x;
            int bullet_y;
            int bullet_speedY = -6;
            MyBullet bullet = null;

            switch (grade) {
                case 1:
                    bullet_x = getX() + 56;
                    bullet_y = getY() - 44;
                    try {
                        bullet = new MyBullet(bullet_x, bullet_y, bullet_speedY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myBulletList.add(bullet);
                    break;
                case 2:
                    bullet_x = getX() + 28;
                    bullet_y = getY() - 44;
                    try {
                        bullet = new MyBullet(bullet_x, bullet_y, bullet_speedY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myBulletList.add(bullet);

                    bullet_x = getX() + 84;
                    bullet_y = getY() - 44;
                    try {
                        bullet = new MyBullet(bullet_x, bullet_y, bullet_speedY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myBulletList.add(bullet);
                    break;
                case 3:
                    bullet_x = getX() + 56;
                    bullet_y = getY() - 44;
                    try {
                        bullet = new MyBullet(bullet_x, bullet_y, bullet_speedY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myBulletList.add(bullet);

                    bullet_x = getX() + 28;
                    bullet_y = getY() - 44;
                    try {
                        bullet = new MyBullet(bullet_x, bullet_y, bullet_speedY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myBulletList.add(bullet);

                    bullet_x = getX() + 84;
                    bullet_y = getY() - 44;
                    try {
                        bullet = new MyBullet(bullet_x, bullet_y, bullet_speedY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myBulletList.add(bullet);
                    break;

                default:
                    break;
            }
            ThreadUtil.sleep(300);
        }
    }

    public void moveX() {
        super.moveX(-50, 400);
    }

    public void moveY() {
        super.moveY(0, 700);
    }
}
