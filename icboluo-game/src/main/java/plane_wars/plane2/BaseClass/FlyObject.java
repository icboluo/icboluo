package plane_wars.plane2.BaseClass;

import lombok.Data;

/**
 * 飞行对象
 *
 * @author icboluo
 */
@Data
public class FlyObject {
    /**
     * x 坐标
     */
    protected int x;
    protected int y;
    /**
     * x 大小
     */
    protected int sizeX;
    protected int sizeY;
    /**
     * x 方向速度
     */
    protected int speedX;
    protected int speedY;

    /**
     * 碰撞攻击力 暂未使用
     */
    protected int crashAtk;
    /**
     * 是否存活
     */
    protected volatile boolean alive = true;

    /**
     * 判断是否相撞
     *
     * @param fo 飞行对象
     * @return 如果相撞返回true
     */
    public boolean isCrash(FlyObject fo) {
        return x + sizeX >= fo.x && y + sizeY >= fo.y && x <= fo.x + fo.sizeX && y <= fo.y + fo.sizeY;
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
