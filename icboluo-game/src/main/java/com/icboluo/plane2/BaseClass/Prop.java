package com.icboluo.plane2.BaseClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author icboluo
 */
public class Prop extends FlyObject {

    String propHpFileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\img_addHp.png";
    String propScoreFileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\img_addScore.png";
    String propUpFileName = "D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\plane2\\z_img\\up.png";
    BufferedImage hp_buffimg;
    BufferedImage score_buffimg;
    BufferedImage up_buffimg;

    int type;
    private final int TYPE_HP = 0;
    private final int TYPE_SCORE = 1;
    private final int TYPE_UP = 2;

    public Prop(int i) throws IOException {
        type = i % 3;

        speedX = 0;
        speedY = 3;

        hp_buffimg = ImageIO.read(new File(propHpFileName));
        score_buffimg = ImageIO.read(new File(propScoreFileName));
        up_buffimg = ImageIO.read(new File(propUpFileName));
    }

    public void drawHpImg(Graphics g) {
        sizeX = 50;
        sizeY = 43;
        g.drawImage(hp_buffimg, x, y, x + sizeX, y + sizeY, 0, 0, 81, 72, null);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawScoreImg(Graphics g) {
        sizeX = 60;
        sizeY = 60;
        g.drawImage(score_buffimg, x, y, x + sizeX, y + sizeY, 0, 0, 58, 59, null);
    }

    public void drawUpImg(Graphics g) {
        sizeX = 50;
        sizeY = 50;
        g.drawImage(up_buffimg, x, y, x + sizeX, y + sizeY, 0, 0, 132, 139, null);
    }


    public void draw(Graphics g) {
        switch (type) {
            case TYPE_HP -> drawHpImg(g);
            case TYPE_SCORE -> drawScoreImg(g);
            case TYPE_UP -> drawUpImg(g);
            default -> {
            }
        }
    }

    public int getType() {
        return type;
    }
}
