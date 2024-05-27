package plane_wars.plane1;

import plane_wars.plane2.PlanConstant;
import lombok.Data;

import javax.swing.*;

/**
 * @author icboluo
 * @since 2022-10-12 19:18
 */
@Data
public class Hero {

    //读取英雄机的图片
    ImageIcon heroImage = new ImageIcon(PlanConstant.GAME1 + "hero.png");
    //英雄机的坐标
    private int x = 300;
    private int y = 400;
    // 分数
    private int number = 0;

    /**
     * 把鼠标的x赋值给英雄机
     *
     * @param x 鼠标的x坐标
     */
    public void moveXto(int x) {
        if (xMid() > GameMain.width) {
            this.x = GameMain.width - heroImage.getIconWidth() - 20;
        } else {
            this.x = x - (heroImage.getIconWidth() / 2);
        }
    }

    public void moveYto(int y) {
        if (y + heroImage.getIconHeight() > GameMain.height) {
            this.y = GameMain.height - heroImage.getIconHeight() - 20;
        } else {
            this.y = y - (heroImage.getIconHeight() / 2);
        }
    }

    /**
     * x轴中心点
     */
    public int xMid() {
        return x + heroImage.getIconWidth() / 2;
    }
}
