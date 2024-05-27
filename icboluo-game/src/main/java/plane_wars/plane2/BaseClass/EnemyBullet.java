package plane_wars.plane2.BaseClass;

import plane_wars.plane2.PlanConstant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 敌人 子弹
 *
 * @author icboluo
 */
public class EnemyBullet extends FlyObject {
    private BufferedImage enemy_bullet_img;

    public EnemyBullet(int x, int y) {
        sizeX = 40;
        sizeY = 40;
        this.x = x;
        this.y = y;
        speedX = 0;
        speedY = 3;
        try {
            enemy_bullet_img = ImageIO.read(new File(PlanConstant.GAME2 + "img_plane_enemy_item.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(enemy_bullet_img, x, y, x + sizeX, y + sizeY, 0, 0, 222, 222, null);
    }
}
