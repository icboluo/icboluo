package com.icboluo.designpattern.behavior.state;

/**
 * @author icboluo
 * @since 2020/11/22 17:53
 */
public class Client {
    public static void main(String[] args) {
        RaffleActitvity actitvity = new RaffleActitvity(1);
        for (int i = 0; i < 3; i++) {
            System.out.println("第" + i + "次抽奖");
            actitvity.debuctMoney();
            actitvity.raffle();
        }
    }
}
