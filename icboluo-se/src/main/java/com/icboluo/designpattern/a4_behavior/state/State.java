package com.icboluo.designpattern.a4_behavior.state;

/**
 * @author icboluo
 * @since 2020/11/22 17:53
 */
public abstract class State {
    /**
     * 扣钱
     */
    public abstract void deductMoney();

    /**
     * 抽奖
     *
     * @return 抽奖结果
     */
    public abstract boolean raffle();

    /**
     * 分配奖品
     */
    public abstract void dispensePrize();
}
