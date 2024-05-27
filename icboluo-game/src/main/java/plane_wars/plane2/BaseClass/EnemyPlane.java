package plane_wars.plane2.BaseClass;

import plane_wars.plane2.PlanConstant;
import com.icboluo.util.RandomUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 敌人 飞机
 *
 * @author icboluo
 */
public class EnemyPlane extends FlyObject {
    private BufferedImage planeImg;

    public EnemyPlane() {
        sizeX = 90;
        sizeY = 70;
        x = RandomUtil.nextInt(410);
        y = -50;
        speedX = 0;
        speedY = 1;
        try {
            planeImg = ImageIO.read(new File(PlanConstant.GAME2 + "img_plane_enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(planeImg, x, y, x + sizeX, y + sizeY, 0, 0, 264, 200, null);
    }
}
