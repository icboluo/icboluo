package com.icboluo.seee.redpackage;

import java.util.ArrayList;

/**
 * @author icboluo
 * @date 2020/6/15 10:50
 */
public class QunZhu extends User {
    public QunZhu(String name, double money) {
        super(name, money);
    }

    public ArrayList<Double> send(double money, int count) {
        double leftMoney = getLeftMoney();
        if (money * count > leftMoney) {
            return null;
        }
        setLeftMoney(leftMoney - money * count);
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(money);
        }
        return list;
    }

}
