package com.icboluo.plane2;

import com.icboluo.plane2.BaseClass.*;
import com.icboluo.plane2.Thread.CrashThread;
import com.icboluo.plane2.Thread.DrawThread;
import com.icboluo.plane2.Thread.EnemyPlaneThread;
import com.icboluo.plane2.Thread.MoveThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public volatile static Player player;
    /**
     * 我的飞机
     */
    public volatile static MyPlane myPlane;

    /**
     * 道具列表
     */
    public static List<Prop> myPropList;
    /**
     * 敌机列表
     */
    public static List<EnemyPlane> enemyPlanes;

    /**
     * 敌方子弹
     */
    public static List<EnemyBullet> enemyBullets;

    static CompletableFuture<Void> a;
    static CompletableFuture<Void> b;
    static CompletableFuture<Void> c;
    static CompletableFuture<Void> d;

    public static void init() {
        player = new Player();
        myPropList = Collections.synchronizedList(new ArrayList<>());
        enemyPlanes = Collections.synchronizedList(new ArrayList<>());
        enemyBullets = Collections.synchronizedList(new ArrayList<>());
    }

    public static void start() {
        a = CompletableFuture.runAsync(new CrashThread());
        b = CompletableFuture.runAsync(new DrawThread());
        c = CompletableFuture.runAsync(new EnemyPlaneThread());
        d = CompletableFuture.runAsync(new MoveThread());
    }

    public static boolean isEnd() {
        return CompletableFuture.allOf( c, d).isDone();
    }
}
