package com.icboluo.leetcode.dp;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-06-05 23:16
 */
class N0322_零钱兑换 {
    // 完全背包问题
    public int coinChange(int[] coins, int amount) {
        // i元最少需要多少个硬币
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        // 循环不分先后：总的来说，就是修正当前总金额使用当前银币的最小次数
        // 硬币种类
        for (int coin : coins) {
            // 总金币
            for (int j = 0; j <= amount; j++) {
                // 如果能用到当前的硬币
                if (j - coin >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        // 如果一种组合数都没有，就算了
        return dp[amount] == Integer.MAX_VALUE / 2 ? -1 : dp[amount];
    }
}
