package com.icboluo.plane2.UI;


import com.icboluo.plane2.Thread.*;

import javax.swing.*;
import java.awt.*;


public class GameUI extends JFrame {
    KListener kl = new KListener();
    Graphics g;
    JPanel game;

    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        gameUI.init();
        gameUI.kl.gameUI = gameUI;
    }

    public void init() {
        JFrame jf = new JFrame("飞机大战"); //创建窗体
        jf.setSize(670, 800);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(new BorderLayout()); //布局

        // 创建三个JPanel,左上为按钮，左下为分数显示 右为游戏页面
        JPanel left = new JPanel();
        JPanel leftUp = new JPanel();
        JPanel leftDown = new JPanel();
        game = new JPanel();

        left.setPreferredSize(new Dimension(170, 800));
        left.setBackground(new Color(-3355444));
        jf.add(left, BorderLayout.WEST);

        jf.add(game, BorderLayout.CENTER);
        game.addMouseListener(kl);     //添加监听器
        game.addMouseMotionListener(kl);
        game.addKeyListener(kl);
        game.requestFocus();
        left.setLayout(new BorderLayout());

        leftUp.setPreferredSize(new Dimension(0, 250));
        leftUp.setBackground(new Color(-3355444));
        left.add(leftUp, BorderLayout.NORTH);

        leftDown.setBackground(new Color(-6710887));
        leftDown.setPreferredSize(new Dimension(0, 550));
        left.add(leftDown, BorderLayout.SOUTH);


        //添加按钮
        addButton(leftUp);
        //添加标签（游戏说明）
        addLabel(leftDown);

        jf.setVisible(true);
        game.requestFocus();
        g = game.getGraphics();

        new Thread(new DrawThread(g)).start();

        new Thread(new MoveThread()).start();

        new Thread(new EnemyPlaneThread()).start();

        new Thread(new TestCrashThread()).start();
    }

    //leftUp部分添加按钮
    public void addButton(JComponent component) {
        String[] str = {"开始游戏", "选择飞机", "选择地图"};
        Dimension dim = new Dimension(90, 35);
        for (String s : str) {
            JButton btn = new JButton(s);
            btn.addActionListener(kl);
            btn.setPreferredSize(dim);
            //监听器
            component.add(btn);
        }
    }

    public void addLabel(JComponent component) {
        String[] str = {"  123131", "游戏规则：", "       玩家可以通过↑ ↓ ← → ", "控制飞机移动，攻击敌机，", "拾取敌机掉落的道具可以", "获得更高的分数，并升级", "你的飞机。"
                , "      当你的飞机到达3级满级", "后，拾取升级道具，将只会", "获得额外分数。"};
        Dimension dim = new Dimension(150, 25);
        for (String s : str) {
            JLabel jl = new JLabel(s);
            jl.setPreferredSize(dim);
            component.add(jl);
        }
    }

    @SuppressWarnings("all")
    public static void gameOver() {
        DrawThread.myPlane.setSpeedX(0);
        DrawThread.myPlane.setSpeedY(0);
        String[] options = {"再来一次", "结束游戏"};
        int value = JOptionPane.showOptionDialog(null, "本局得分为：" + DrawThread.player.score + ",要再来一局吗",
                "游戏结束", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, "再来一次");
        if (value != JOptionPane.CLOSED_OPTION) {
            switch (value) {
                case 0:
                    DrawThread.player.score = 0;
                    DrawThread.player.hp = 100;
                    DrawThread.myPlane.setGrade(1);
                    for (int i = 0; i < EnemyPlaneThread.enemyPlanes.size(); i++) {
                        EnemyPlaneThread.enemyPlanes.get(i).setAlive(false);
                    }
                    EnemyPlaneThread.enemyPlanes.clear();
                    EnemyBulletThread.enemyBullets.clear();
                    DrawThread.myPlane.setX(200);
                    DrawThread.myPlane.setY(600);
                    DrawThread.player.setAlive(true);

                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }
}



