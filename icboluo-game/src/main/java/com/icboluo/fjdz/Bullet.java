package com.icboluo.fjdz;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

/**
 * 子弹对象
 */
@Data
public class Bullet {

    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon bulletImageIcon = new ImageIcon("D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\fjdz\\image/bullet.png");

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = bulletImageIcon.getIconWidth();
        this.height = bulletImageIcon.getIconHeight();
    }

    public void move() {
        this.y -= 4;
    }

    public void drawImage(Graphics g) {
        g.drawImage(bulletImageIcon.getImage(), x, y, null);
    }
}
