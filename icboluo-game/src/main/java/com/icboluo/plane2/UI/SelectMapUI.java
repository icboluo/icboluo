package com.icboluo.plane2.UI;

import javax.swing.*;
import java.awt.*;

public class SelectMapUI {
    KListener kl;

    public SelectMapUI(KListener kl) {
        this.kl = kl;
    }

    public void init() {
        // 创建窗体
        JFrame jf = new JFrame("选择地图");
        jf.setSize(300, 400);
        jf.setLocationRelativeTo(kl.gameUI);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jf.add(jPanel);
        add(jPanel);
        addButton(jPanel);
        jf.setVisible(true);

    }

    public void add(JComponent component) {
        //创建4个单选框按钮
        String[] str = {"森林上空", "天空之城", "沙漠荒野", "实验区域"};
        Dimension dim = new Dimension(200, 60);
        ButtonGroup group = new ButtonGroup();
        for (String s : str) {
            JRadioButton c = new JRadioButton(s);
            c.addActionListener(kl);
            c.setPreferredSize(dim);
            //监听器
            group.add(c);
            component.add(c);
        }
    }

    public void addButton(JComponent component) {
        String[] str = {"确定", "取消"};
        Dimension dim = new Dimension(100, 40);
        for (String s : str) {
            JButton btn = new JButton(s);
            btn.addActionListener(kl);
            btn.setPreferredSize(dim);
            //监听器
            component.add(btn);
        }
    }
}
