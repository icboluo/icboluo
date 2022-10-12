package com.icboluo.plane2.UI;

import com.icboluo.plane2.Thread.DrawThread;

import java.awt.event.*;

public class KListener implements ActionListener, KeyListener, MouseListener , MouseMotionListener {
    GameUI gameUI;
    int speed = 5;
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn_action = e.getActionCommand();
        System.out.println(btn_action);
        if(btn_action.equals("选择地图")){
            SelectMapUI selectMapUI = new SelectMapUI(gameUI.kl);
            selectMapUI.init();
        }

        switch(btn_action){
//            case "开始游戏":
//                new Thread(new DrawThread(gameUI.g)).start();
//                new Thread(new MoveThread()).start();
//                new Thread(new EnemyPlaneThread()).start();
//                new Thread(new TestCrashThread()).start();
//                break;
            case "森林上空" :
                DrawThread.player.mapNum=1;
                gameUI.game.requestFocus();
                break;
            case "天空之城" :
                DrawThread.player.mapNum=2;
                gameUI.game.requestFocus();
                break;
            case "沙漠荒野" :
                DrawThread.player.mapNum=3;
                gameUI.game.requestFocus();
                break;
            case "实验区域" :
                DrawThread.player.mapNum=4;
                gameUI.game.requestFocus();
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(DrawThread.myPlane!=null){
            switch (c){
                case 37:
                    DrawThread.myPlane.setSpeedX(-speed);
                    break;
                case 38:
                    DrawThread.myPlane.setSpeedY(-speed);
                    break;
                case 39:
                    DrawThread.myPlane.setSpeedX(speed);
                    break;
                case 40:
                    DrawThread.myPlane.setSpeedY(speed);
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        switch (c){
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
//        int x = e.getX();
//        int y = e.getY();
//        System.out.println(x + " " + y);
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








