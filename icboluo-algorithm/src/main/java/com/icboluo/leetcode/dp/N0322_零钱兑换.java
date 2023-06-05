package com.icboluo.leetcode.dp;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-06-05 23:16
 */
class N0322_零钱兑换 {
    // 完全背包问题
    public int coinChange(int[] coins, int amount) {
        // i元需要多少个硬币
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        // 循环不分先后：总的来说，就是修正当前总金额使用当前银币的最小次数
        for (int coin : coins) {
            for (int j = 0; j <= amount; j++) {
                if (j - coin >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE / 2 ? -1 : dp[amount];
    }
}
