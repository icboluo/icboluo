package com.icboluo.plane2;

import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
public class SelectMapUI {

    public void init() {
        // 创建窗体
        JFrame jf = new JFrame("选择地图");
        jf.setSize(300, 400);
        jf.setLocationRelativeTo(GameBusiness.frame);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jf.add(jPanel);

        add(jPanel);
        addButton(jPanel);

        jf.setVisible(true);
    }

    public void add(JComponent component) {
        //创建4个单选框按钮
        String[] arr = {"森林上空", "天空之城", "沙漠荒野", "实验区域"};
        Dimension dim = new Dimension(200, 60);
        ButtonGroup group = new ButtonGroup();
        for (String str : arr) {
            JRadioButton c = new JRadioButton(str);
            c.addActionListener(GameBusiness.listener);
            c.setPreferredSize(dim);
            //监听器
            group.add(c);
            component.add(c);
        }
    }

    public void addButton(JComponent component) {
        String[] arr = {"确定", "取消"};
        Dimension dim = new Dimension(100, 40);
        for (String str : arr) {
            JButton btn = new JButton(str);
            btn.addActionListener(GameBusiness.listener);
            btn.setPreferredSize(dim);
            //监听器
            component.add(btn);
        }
    }
}
