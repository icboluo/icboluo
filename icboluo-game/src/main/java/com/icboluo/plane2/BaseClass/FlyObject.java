package com.icboluo.plane2.BaseClass;

import lombok.Data;

/**
 * 飞行对象
 */
@Data
public class FlyObject {
    protected int x;
    protected int y;
    protected int sizeX;
    protected int sizeY;
    protected int speedX;
    protected int speedY;
    protected volatile boolean alive = true;

    // 判断是否相撞
    public boolean judge_crash(FlyObject fo) {
        if (x + sizeX < fo.x || y + sizeY < fo.y || x > fo.x + fo.sizeX || y > fo.y + fo.sizeY) {
            return false;
        } else {
            return true;
        }
    }

    public void move() {
        x = x + speedX;
        y = y + speedY;
    }

    public void moveX(int min, int max) {
        if (x >= min && x <= max) {
            x = x + speedX;
        }
        // 不能飞到窗体外边
        if (x < min) {
            x = min;
        }
        if (x > max) {
            x = max;
        }
    }

    public void moveY(int min, int max) {
        if (y >= min && y <= max) {
            y = y + speedY;
        }
        if (y < min) {
            y = min;
        }
        if (y > max) {
            y = max;
        }
    }
}
