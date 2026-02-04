package com.icboluo.leetcode.dp;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-09-18 0:25
 */
public class N0416_0518_背包问题 {
    public void test() {
        System.out.println(test1(new int[] {2, 1, 3}, new int[] {4, 2, 3}, 5));
        System.out.println(canPartition(new int[] {1, 5, 11, 5}));
        System.out.println(change(5, new int[] {1, 2, 5}));
    }

    /**
     * <p>背包问题：此类问题没有什么技巧，只能穷举所有可能
     * <p>0-1 背包
     * <p>状态：背包的容量，可选择的物品
     * <p>选择：装进背包，不装进背包
     * <p>这个解法类似于floyd算法，做累加，将每种组合情况均累加到dp中即可
     *
     * @param weight 第i个物品的重量
     * @param val 第i个物品的价值
     * @param target 总质量
     */
    public int test1(int[] weight, int[] val, int target) {
        // dp[i][j]:对于前i个物品，当当前背包容量是j的时候，可以装的最大价值
        int[][] dp = new int[weight.length + 1][target + 1];
        // dp初始化，其实这里可以不用初始化
        for (int i = 0; i < weight.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < target; j++) {
            dp[0][j] = 0;
        }
        // 对于x，y的关系，这两个并没有严格的顺序关系，总物品变化也好、总质量变化也好，均可（for循环怎么写都行）
        // 对于状态转移方程，选择的是是否装进背包，对象是物品，所以状态转移方程是基于物品的变化而变化（）物品+-1
        // 这里使用dp比较方便，我们的目的是求dp的值
        for (int i = 1; i < dp.length; i++) {  // 物品
            for (int j = 1; j < dp[i].length; j++) { // 容量
                int curWeight = weight[i - 1];
                // 这个dp先行一步，这个是个大坑
                // 当前物品的质量大于总质量的时候，取上次的即可
                if (curWeight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 能放得下，取上次的或者将当前物品放入求最优解
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curWeight] + val[i - 1]);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    /**
     * N416 0-1背包变体：划分相等的子集
     */
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // 从0-1背包中选择一部分数字，保证和为target即可
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int curWeight = nums[i - 1];
                // 放不进去不要放
                if (curWeight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - curWeight];
                }
                // 仔细观察，dp[i]====一直等于====dp[i-1]直接删掉好了
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    /**
     * 518 银币找零
     * 完全背包
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // dp[i]代表只使用前i个物品，背包容量为j时，有多少种方法可以装满
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int curWeight = coins[i - 1];
                if (curWeight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 后面的这个dp[i]是0-1背包和完全背包唯一的不同点，这里用i表示i可以重复使用
                    dp[i][j] = dp[i - 1][j] + dp[i][j - curWeight];
                }
                //     这块也是可以删除多余的[i]进行优化的，不过不清楚为什么
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
