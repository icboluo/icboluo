package com.icboluo.plane_wars.plane2;

import plane_wars.plane2.Thread.DrawThread;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.*;
import java.util.concurrent.CompletableFuture;

import static com.icboluo.plane_wars.plane2.AtkAll.myPlane;
import static com.icboluo.plane_wars.plane2.AtkAll.player;

/**
 * @author icboluo
 */
@Slf4j
public class Listener implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

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
                    AtkAll.start();
                    CompletableFuture.runAsync(new DrawThread()).exceptionally(ex -> {
                        log.error("b", ex);
                        return null;
                    });
                    isStart = true;
                }
                GameBusiness.panel.requestFocus();
                break;
            case "森林上空":
                player.mapNum = 1;
                GameBusiness.panel.requestFocus();
                break;
            case "天空之城":
                player.mapNum = 2;
                GameBusiness.panel.requestFocus();
                break;
            case "沙漠荒野":
                player.mapNum = 3;
                GameBusiness.panel.requestFocus();
                break;
            case "实验区域":
                player.mapNum = 4;
                GameBusiness.panel.requestFocus();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (myPlane != null) {
            switch (keyCode) {
                case 37 -> {
                    myPlane.setSpeedX(-speed);
                }
                case 38 -> {
                    myPlane.setSpeedY(-speed);
                }
                case 39 -> {
                    myPlane.setSpeedX(speed);
                }
                case 40 -> {
                    myPlane.setSpeedY(speed);
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
                myPlane.setSpeedX(0);
                break;
            case 38:
            case 40:
                myPlane.setSpeedY(0);
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








