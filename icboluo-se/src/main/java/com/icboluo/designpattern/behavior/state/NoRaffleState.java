package com.icboluo.designpattern.behavior.state;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020/11/22 17:54
 */
@AllArgsConstructor
public class NoRaffleState extends State {
    RaffleActitvity actitvity;

    @Override
    public void deductMoney() {
        System.out.println("扣除50积分可以抽奖");
        actitvity.setState(actitvity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("没有扣积分，不能抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("不能发放奖品");
    }
}
