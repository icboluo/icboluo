package plane_wars.plane1;

import plane_wars.plane2.PlanConstant;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 敌人对象
 *
 * @author icboluo
 */
@Data
public class Enemy {

    private int width;//敌人图片的宽度
    private int height;//敌人图片的高度

    //敌人的坐标
    private int x;
    private int y;

    //
    private ImageIcon enemyImageIcon = new ImageIcon(PlanConstant.GAME1 + "enemy.png");

    public Enemy() {
        this.width = enemyImageIcon.getIconWidth();
        this.height = enemyImageIcon.getIconHeight();

        //设置敌机的位置
        Random random = new Random();
        random.nextInt(10);

        this.x = random.nextInt(GameMain.width - (width / 2));
        this.y = -random.nextInt(GameMain.height - (height / 2));
    }

    public void move() {
        this.y += 1; //速度
    }

    public void drawImage(Graphics g) {
        g.drawImage(enemyImageIcon.getImage(), x, y, null);
    }
}
