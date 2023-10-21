package com.icboluo.leetcode.dp;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-06-05 23:16
 */
class N0322_0518_零钱兑换 {
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

    /**
     * 322 零钱兑换 暴力解法
     *
     * @param arr
     * @param target
     * @return
     */
    private int m1(int[] arr, int target) {
        int min = Integer.MAX_VALUE;
        int[] dp = new int[target + 1];

        dp[10] = 2;
        dp[9] = 4;
        dp[6] = 2;
        dp[11] = dp[10] + 1;
        dp[11] = Math.min(dp[9] + 1, dp[11]);
        dp[11] = Math.min(dp[6] + 1, dp[11]);

        for (int coin : arr) {
            // 11-1+1 11-2+1 11-5+1
            final int need = dp[target - coin] + 1;
            min = Math.min(min, need);
        }

        return dp[target];
    }

    // 0518
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        // j使用第一种i类型的硬币组成的组合数
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                // 不使用当前 + 使用当前硬币（如果金额大于硬币面值，才有必要计算，计算剩余金额使用当前硬币的组合数
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // j使用第一种i类型的硬币组成的组合数
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 不使用当前 + 使用当前硬币（如果金额大于硬币面值，才有必要计算，计算剩余金额使用当前硬币的组合数
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
