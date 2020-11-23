package com.icboluo.designpattern.state;

import lombok.AllArgsConstructor;

/**
 * 奖品发完
 *
 * @author icboluo
 * @date 2020/11/22 17:54
 */
@AllArgsConstructor
public class DispenseOutState extends State {
    RaffleActitvity actitvity;

    @Override
    public void deductMoney() {
        System.out.println("奖品发完了，不能参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发完了，不能参加");
        return false;

    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品发完了，不能参加");
    }
}
