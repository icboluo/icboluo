package com.icboluo.plane2.BaseClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    public int score;
    public int hp = 100;
    public int mapNum = 1;
    private volatile boolean alive = true;
    String numFileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\img_score.png";
    String hpFileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\hp.png";
    String scoreFileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\score_img.png";
    BufferedImage score_img;
    BufferedImage hp_img;
    BufferedImage score_lable_img;

    int width = 22;
    int height = 32;

    public Player() throws IOException {
        score = 0;
        score_img = ImageIO.read(new File(numFileName));
        hp_img = ImageIO.read(new File(hpFileName));
        score_lable_img = ImageIO.read(new File(scoreFileName));

    }

    public void drawHp(Graphics g) {
        int num1 = hp / 100;
        int num2 = hp / 10 % 10;
        int num3 = hp % 10;
        int x = 60;
        int y = 55;
        g.drawImage(hp_img, 20, y, 59, y + height, 0, 0, 64, 37, null);
        g.drawImage(score_img, x, y, x + width, y + height, width * num1, 0, width * num1 + width, 32, null);
        g.drawImage(score_img, x + width, y, x + 2 * width, y + height, width * num2, 0, width * num2 + width, 32, null);
        g.drawImage(score_img, x + width * 2, y, x + width * 3, y + height, width * num3, 0, width * num3 + width, 32, null);

    }


    public void drawScore(Graphics g) {
        int n = score % 1000;
        int num1 = n / 100;
        int num2 = n / 10 % 10;
        int num3 = n % 10;
        int x = 60;
        int y = 20;
        g.drawImage(score_lable_img, 20, y, 59, y + height, 0, 0, 107, 54, null);
        g.drawImage(score_img, x, y, x + width, y + height, width * num1, 0, width * num1 + width, 32, null);
        g.drawImage(score_img, x + width, y, x + 2 * width, y + height, width * num2, 0, width * num2 + width, 32, null);
        g.drawImage(score_img, x + width * 2, y, x + width * 3, y + height, width * num3, 0, width * num3 + width, 32, null);

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
