package com.icboluo.seee.redpackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author icboluo
 * @date 2020/6/15 10:51
 */
public class RedDemo {
    public static void main(String[] args) {
        QunZhu qz = new QunZhu("群主", 200);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入红包金额:");
        int money = sc.nextInt();
        System.out.println("请输入红包个数:");
        int count = sc.nextInt();

        ArrayList<Double> sendList = qz.send(money, count);
        if (sendList == null) {
            System.out.println("余额不足");
            return;
        }
        Member m1 = new Member();
        Member m2 = new Member();
        Member m3 = new Member();

        m1.openHongbao(sendList);
        m2.openHongbao(sendList);
        m3.openHongbao(sendList);

        qz.show();
        m1.show();
        m2.show();
        m3.show();
    }
}
