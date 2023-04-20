package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-04-21 1:09
 */
class N2591_将钱分配给最多的孩子 {
    // FIXME ERROR 这不是一个好问题
    public int distMoney(int money, int children) {
        // 钱不够分
        if (children > money) {
            return -1;
        }
        money = money - children;
        // 每个人都8美元
        if (money / 7 == children && money % 7 == 0) {
            return children;
        }
        return -1;
    }
}
