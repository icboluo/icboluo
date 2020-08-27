package com.icboluo.seee.redpackage;

import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author icboluo
 * @date 2020/6/15 10:49
 */
@NoArgsConstructor
public class Member extends User {

    public Member(String username, double leftMoney) {
        super(username, leftMoney);
    }

    public void openHongbao(ArrayList<Double> list) {
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("红包派完了");
        }
        Random r = new Random();
        int index = r.nextInt(list.size());
        Double money = list.remove(index);
        double leftMoney = getLeftMoney();
        leftMoney += money;
        setLeftMoney(leftMoney);


    }
}
