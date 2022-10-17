package com.icboluo.plane2.UI;

import com.icboluo.plane2.Thread.DrawThread;

import java.awt.event.*;

/**
 * @author icboluo
 */
public class Listener implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    GameStart gameStart;
    int speed = 5;

    private boolean isStart;

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println(actionCommand);
        if ("选择地图".equals(actionCommand)) {
            SelectMapUI selectMapUI = new SelectMapUI();
            selectMapUI.init();
        }

        switch (actionCommand) {
            case "开始游戏":
                if (!isStart) {
//                    new Thread(new DrawThread(gameUI.g)).start();
//                    new Thread(new MoveThread()).start();
//                    new Thread(new EnemyPlaneThread()).start();
//                    new Thread(new TestCrashThread()).start();
                    isStart = true;
                }
                break;
            case "森林上空":
                DrawThread.player.mapNum = 1;
                GameStart.game.requestFocus();
                break;
            case "天空之城":
                DrawThread.player.mapNum = 2;
                GameStart.game.requestFocus();
                break;
            case "沙漠荒野":
                DrawThread.player.mapNum = 3;
                GameStart.game.requestFocus();
                break;
            case "实验区域":
                DrawThread.player.mapNum = 4;
                GameStart.game.requestFocus();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @SuppressWarnings("all")
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (DrawThread.myPlane != null) {
            switch (keyCode) {
                case 37 -> {
                    DrawThread.myPlane.setSpeedX(-speed);
                }
                case 38 -> {
                    DrawThread.myPlane.setSpeedY(-speed);
                }
                case 39 -> {
                    DrawThread.myPlane.setSpeedX(speed);
                }
                case 40 -> {
                    DrawThread.myPlane.setSpeedY(speed);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        switch (c) {
            case 37:
            case 39:
                DrawThread.myPlane.setSpeedX(0);
                break;
            case 38:
            case 40:
                DrawThread.myPlane.setSpeedY(0);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + " " + y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}








