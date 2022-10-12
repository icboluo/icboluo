package com.icboluo.fjdz;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

/**
 * @author icboluo
 */
@Data
public class Bomb {

	private int x;
	private int y;
	private int width;
	private int height;
	private ImageIcon bombimg = new ImageIcon("D:\\IdeaProjects\\icboluo\\icboluo-game\\src\\main\\java\\com\\icboluo\\fjdz\\image/bomb.png");
	private int count;//删除的次数

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = bombimg.getIconWidth();
		this.height = bombimg.getIconHeight();
	}

	public void drawImage(Graphics g) {
		g.drawImage(bombimg.getImage(), x, y, null);
	}


	public void move() {
		count++;
	}
}
