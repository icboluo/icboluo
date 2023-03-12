package com.icboluo.designpattern.a4_behavior.state;

import com.icboluo.util.RandomHelper;
import lombok.AllArgsConstructor;

/**
 * 可以抽奖的状态
 *
 * @author icboluo
 * @since 2020/11/22 17:54
 */
@AllArgsConstructor
public class CanRaffleState extends State {
    RaffleActitvity actitvity;

    @Override
    public void deductMoney() {
        System.out.println("扣除50积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("正在抽奖");
        int[] random = RandomHelper.getRandom(10);
        if (random[0] % 2 == 0) {
            actitvity.setState(actitvity.getDispenseState());
            return true;
        } else {
            System.out.println("没有抽中");
            actitvity.setState(actitvity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("没中奖不能发放奖品");
    }
}
