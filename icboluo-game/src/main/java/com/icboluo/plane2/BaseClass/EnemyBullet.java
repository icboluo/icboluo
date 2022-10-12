package com.icboluo.plane2.BaseClass;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 敌人 子弹
 *
 * @author icboluo
 */
public class EnemyBullet extends FlyObject {
    private String fileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\img_plane_enemy_item.png";
    private BufferedImage enemy_bullet_img;

    @SneakyThrows
    public EnemyBullet(int x, int y) {
        enemy_bullet_img = ImageIO.read(new File(fileName));
        sizeX = 40;
        sizeY = 40;
        this.x = x;
        this.y = y;
        speedX = 0;
        speedY = 3;
    }

    public void draw(Graphics g) {
        g.drawImage(enemy_bullet_img, x, y, x + sizeX, y + sizeY, 0, 0, 222, 222, null);
    }
}
