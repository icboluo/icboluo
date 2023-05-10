package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @since 2022-12-28 22:53
 */
class N0746_最低成本爬楼梯 {
    /**
     * 1次可以挨着走一阶或者跳着走2阶
     * 70... 还有一些mid问题没有做 TODO
     *
     * @param cost 爬到这个楼梯的花费
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }
}
