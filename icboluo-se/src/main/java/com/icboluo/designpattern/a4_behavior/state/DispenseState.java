package com.icboluo.designpattern.a4_behavior.state;

import lombok.AllArgsConstructor;

/**
 * 发放奖品的状态
 *
 * @author icboluo
 * @since 2020/11/22 17:54
 */
@AllArgsConstructor
public class DispenseState extends State {
    RaffleActitvity actitvity;

    @Override
    public void deductMoney() {
        System.out.println("不能扣除积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("不能抽奖");
        return false;

    }

    @Override
    public void dispensePrize() {
        if (actitvity.getCount() > 0) {
            System.out.println("恭喜中奖");
            actitvity.setState(actitvity.getNoRaffleState());
        }else {
            System.out.println("奖品发完了");
            actitvity.setState(actitvity.getDispenseOutState());
        }
    }
}
