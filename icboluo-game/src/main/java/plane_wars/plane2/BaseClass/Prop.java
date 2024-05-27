package plane_wars.plane2.BaseClass;

import plane_wars.plane2.PlanConstant;
import com.icboluo.util.RandomUtil;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 道具
 *
 * @author icboluo
 */
@Data
public class Prop extends FlyObject {

    /**
     * 心形 图形---加血量
     */
    BufferedImage hpImg;
    /**
     * 五角星---得分
     */
    BufferedImage fivePointedStarImg;
    /**
     * up心 图形---等级提升
     */
    BufferedImage upHeartImg;

    /**
     * 道具类型 0代表心形，1代表五角星，2代表up心
     */
    int type;

    public Prop() {
        type = RandomUtil.nextInt(10) % 3;

        speedX = 0;
        speedY = 3;
        try {
            hpImg = ImageIO.read(new File(PlanConstant.GAME2 + "img_addHp.png"));
            fivePointedStarImg = ImageIO.read(new File(PlanConstant.GAME2 + "img_addScore.png"));
            upHeartImg = ImageIO.read(new File(PlanConstant.GAME2 + "up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawHpImg(Graphics g) {
        sizeX = 50;
        sizeY = 43;
        g.drawImage(hpImg, x, y, x + sizeX, y + sizeY, 0, 0, 81, 72, null);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawScoreImg(Graphics g) {
        sizeX = 60;
        sizeY = 60;
        g.drawImage(fivePointedStarImg, x, y, x + sizeX, y + sizeY, 0, 0, 58, 59, null);
    }

    public void drawUpImg(Graphics g) {
        sizeX = 50;
        sizeY = 50;
        g.drawImage(upHeartImg, x, y, x + sizeX, y + sizeY, 0, 0, 132, 139, null);
    }


    public void draw(Graphics g) {
        switch (type) {
            case 0 -> drawHpImg(g);
            case 1 -> drawScoreImg(g);
            case 2 -> drawUpImg(g);
        }
    }
}
