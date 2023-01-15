package com.icboluo.plane2.BaseClass;

import com.icboluo.plane2.PlanConstant;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.icboluo.plane2.AtkAll.myPlane;

/**
 * @author icboluo
 */
@Data
public class Player {
    public int score;
    public int hp;
    public int mapNum = 1;
    private volatile boolean alive;
    /**
     * 得分具体数字 图像
     */
    BufferedImage scoreNumImg;
    /**
     * 血量图像
     */
    BufferedImage hpImg;
    /**
     * 分数2个字 图像
     */
    BufferedImage scoreLabelImg;

    int width = 22;
    int height = 32;

    public Player() {
        score = 0;
        hp = 100;
        alive = true;
        try {
            // 不要 @SneakyThrows,会吃掉异常，好像也不会吃掉异常
            scoreNumImg = ImageIO.read(new File(PlanConstant.GAME2 + "img_score.png"));
            hpImg = ImageIO.read(new File(PlanConstant.GAME2 + "hp.png"));
            scoreLabelImg = ImageIO.read(new File(PlanConstant.GAME2 + "score_img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawHp(Graphics g) {
        int num1 = hp / 100;
        int num2 = hp / 10 % 10;
        int num3 = hp % 10;
        int x = 60;
        int y = 55;
        g.drawImage(hpImg, 20, y, 59, y + height, 0, 0, 64, 37, null);
        g.drawImage(scoreNumImg, x, y, x + width, y + height, width * num1, 0, width * num1 + width, 32, null);
        g.drawImage(scoreNumImg, x + width, y, x + 2 * width, y + height, width * num2, 0, width * num2 + width, 32, null);
        g.drawImage(scoreNumImg, x + width * 2, y, x + width * 3, y + height, width * num3, 0, width * num3 + width, 32, null);
    }


    public void drawScore(Graphics g) {
        int n = score % 1000;
        int num1 = n / 100;
        int num2 = n / 10 % 10;
        int num3 = n % 10;
        int x = 60;
        int y = 20;
        g.drawImage(scoreLabelImg, 20, y, 59, y + height, 0, 0, 107, 54, null);
        g.drawImage(scoreNumImg, x, y, x + width, y + height, width * num1, 0, width * num1 + width, 32, null);
        g.drawImage(scoreNumImg, x + width, y, x + 2 * width, y + height, width * num2, 0, width * num2 + width, 32, null);
        g.drawImage(scoreNumImg, x + width * 2, y, x + width * 3, y + height, width * num3, 0, width * num3 + width, 32, null);
    }

    /**
     * 吃道具
     *
     * @param prop 道具
     */
    public void eatProp(Prop prop) {
        int type = prop.type;
        if (type == 0) {
            if (hp >= 90) {
                hp = 100;
            } else {
                hp += 10;
            }
        } else if (type == 1) {
            score += 20;
        } else if (type == 2) {
            if (myPlane.getGrade() <= 2) {
                myPlane.addGrade();
            } else {
                score += 20;
            }
        }
    }

    /**
     * 碰撞敌机
     *
     * @param enemyPlane 敌机
     */
    public void crashEnemyPlane(EnemyPlane enemyPlane) {
        if (hp > 20) {
            hp -= 20;
            score += 5;
        } else {
            hp = 0;
            alive = false;
        }
    }

    /**
     * 碰撞敌机子弹
     *
     * @param enemyBullet 敌机子弹
     */
    public void crashEnemyBullet(EnemyBullet enemyBullet) {
        if (hp > 5) {
            hp -= 5;
            score += 1;
        } else {
            hp = 0;
            alive = false;
        }
    }
}
