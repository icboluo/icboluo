package com.icboluo.plane2;

import com.icboluo.plane2.BaseClass.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 计算机
 *
 * @author icboluo
 * @since 2023-01-15 22:44
 */
public class AtkAll {
    /**
     * 玩家
     */
    public volatile static Player player = new Player();
    /**
     * 我的飞机
     */
    public volatile static MyPlane myPlane;

    /**
     * 道具列表
     */
    public static List<Prop> myPropList = Collections.synchronizedList(new ArrayList<>());
    /**
     * 敌机列表
     */
    public static final List<EnemyPlane> enemyPlanes = Collections.synchronizedList(new ArrayList<>());

    /**
     * 敌方子弹
     */
    public static final List<EnemyBullet> enemyBullets = Collections.synchronizedList(new ArrayList<>());

}
