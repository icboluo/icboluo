package plane_wars.plane2.Thread;

import plane_wars.plane2.BaseClass.EnemyPlane;
import com.icboluo.util.RandomUtil;
import com.icboluo.util.SimpleThreadUtil;

import static com.icboluo.plane_wars.plane2.AtkAll.enemyPlanes;
import static com.icboluo.plane_wars.plane2.AtkAll.player;

/**
 * 敌方飞机线程
 *
 * @author icboluo
 */
public class EnemyPlaneThread implements Runnable {

    /**
     * 不断地在上方产生敌方飞机，并给每一个飞机创建发射子弹的线程
     */
    @Override
    public void run() {
        int sleepTime = 800;
        while (player.isAlive()) {
            if (player.score >= 500) {
                sleepTime = 300;
            }
            EnemyPlane enemyPlane = new EnemyPlane();
            enemyPlanes.add(enemyPlane);
            new Thread(new EnemyBulletThread(enemyPlane)).start();
            SimpleThreadUtil.sleep(sleepTime + RandomUtil.nextInt(300));
        }
    }
}
