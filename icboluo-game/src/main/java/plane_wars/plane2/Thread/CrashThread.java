package plane_wars.plane2.Thread;

import com.icboluo.plane_wars.plane2.AtkAll;
import plane_wars.plane2.BaseClass.EnemyBullet;
import plane_wars.plane2.BaseClass.EnemyPlane;
import plane_wars.plane2.BaseClass.MyPlane;
import plane_wars.plane2.BaseClass.Prop;
import com.icboluo.plane_wars.plane2.GameStart;
import com.icboluo.util.RandomUtil;
import com.icboluo.util.SimpleThreadUtil;

import java.util.List;

import static com.icboluo.plane_wars.plane2.AtkAll.*;

/**
 * 碰撞线程
 */
public class CrashThread implements Runnable {

    @Override
    public void run() {
        // 保证另一个线程的 plane已经被初始化
        while (myPlane == null) {
            // not have method
        }
        while (player.isAlive()) {
            // 我方子弹与敌方飞机碰撞
            synchronized (MyPlane.bulletList) {
                synchronized (enemyPlanes) {
                    for (int i = MyPlane.bulletList.size() - 1; i >= 0; i--) {
                        for (int j = enemyPlanes.size() - 1; j >= 0; j--) {
                            EnemyPlane enemyPlane = enemyPlanes.get(j);
                            // 见鬼了，应该用什么锁才可以
                            if (i < MyPlane.bulletList.size() && MyPlane.bulletList.get(i).isCrash(enemyPlane)) {
                                // 关闭死亡的敌机线程
                                enemyPlane.setAlive(false);
                                player.score += 5;
                                // 打中后随机掉落道具(1/4几率)
                                if (RandomUtil.nextInt(4) == 1) {
                                    Prop prop = new Prop();
                                    prop.setXY(enemyPlane.getX(), enemyPlane.getY());
                                    myPropList.add(prop);
                                }
                                enemyPlanes.remove(j);
                                MyPlane.bulletList.remove(i);
                            }
                        }
                    }
                }
            }

            // 我方子弹与敌方子弹碰撞
            synchronized (MyPlane.bulletList) {
                synchronized (enemyBullets) {
                    for (int i = MyPlane.bulletList.size() - 1; i >= 0; i--) {
                        for (int j = enemyBullets.size() - 1; j >= 0; j--) {
                            if (MyPlane.bulletList.get(i).isCrash(enemyBullets.get(j))) {
                                // 发生碰撞;增加得分，移除碰撞物品
                                player.score += 1;
                                MyPlane.bulletList.remove(i);
                                enemyBullets.remove(j);
                            }
                        }
                    }
                }
            }

            // 与敌方子弹碰撞
            List<EnemyBullet> crashEnemyBulletList = enemyBullets.stream()
                    .filter(myPlane::isCrash)
                    .toList();
            crashEnemyBulletList.forEach(crash -> player.crashEnemyBullet(crash));
            enemyBullets.removeAll(crashEnemyBulletList);

            // 与敌机碰撞；碰撞敌机
            List<EnemyPlane> crashEnemyPlaneList = enemyPlanes.stream()
                    .filter(myPlane::isCrash)
                    .toList();
            crashEnemyPlaneList.forEach(crash -> {
                crash.setAlive(false);
                player.crashEnemyPlane(crash);
            });
            enemyPlanes.removeAll(crashEnemyPlaneList);

            // 获取道具并清理以获取道具；碰撞道具
            List<Prop> crashPropList = myPropList.stream().filter(myPlane::isCrash).toList();
            crashPropList.forEach(player::eatProp);
            myPropList.removeAll(crashPropList);

            SimpleThreadUtil.sleep(5);
        }
        while (!AtkAll.isEnd()) {
            SimpleThreadUtil.sleep(5);
        }
        GameStart.gameOver();
    }
}
