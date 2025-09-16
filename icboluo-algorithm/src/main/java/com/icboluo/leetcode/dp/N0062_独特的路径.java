package com.icboluo.leetcode.dp;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-05-16 21:13
 */
class N0062_独特的路径 {
    public static void main(String[] args) {
        var cla = new N0062_独特的路径();
        System.out.println(cla.uniquePaths(3, 7));
        System.out.println(cla.uniquePaths(3, 2));
    }

    // 从左上到右下的路径个数
    // 当然了，这个方法存在空间浪费，可以优化
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] ints : dp) {
            Arrays.fill(ints, 1);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
