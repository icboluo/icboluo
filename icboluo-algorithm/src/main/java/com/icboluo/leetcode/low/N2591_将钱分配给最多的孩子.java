package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-04-21 1:09
 */
class N2591_将钱分配给最多的孩子 {
    // 这不是一个好问题
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
        // 有一个是4，我们需要再最后2个之间重新分配
        if (money / 7 == children - 1 && money % 7 == 3) {
            return children - 2;
        }
        // 否则，最多只有chil-1活动8美元，如果钱比较少，则只可能是/7
        return Math.min(children - 1, money / 7);
    }
}
