package com.icboluo.designpattern.a4_behavior.state;

import lombok.Data;

/**
 * @author icboluo
 * @since 2020/11/22 17:55
 */
@Data
public class RaffleActitvity {

    State state = null;
    /**
     * 奖品数量
     */
    int count = 0;
    /**
     * 可以抽奖状态
     */
    State canRaffleState = new CanRaffleState(this);
    /**
     * 分配状态
     */
    State dispenseState = new DispenseState(this);
    /**
     * 奖品发完状态
     */
    State dispenseOutState = new DispenseOutState(this);
    /**
     * 不能抽奖状态
     */
    State noRaffleState = new NoRaffleState(this);

    public RaffleActitvity( int count) {
        this.state = getNoRaffleState();
        this.count = count;
    }

    public void debuctMoney() {
        state.deductMoney();
    }

    public void raffle() {
        if (state.raffle()) {
            state.dispensePrize();
        }
    }

    public int getCount() {
        int curCount = count;
        count--;
        return curCount;
    }
}
