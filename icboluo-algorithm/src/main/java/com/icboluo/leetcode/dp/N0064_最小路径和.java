package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @since 2023-05-16 21:05
 */
class N0064_最小路径和 {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 && j == 0) {
                    // 初始化dp
                    dp[i][j] = grid[i][j];
                } else {
                    dp[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 2个角度的最小值
                int min;
                if (i == 0 && j == 0) {
                    min = 0;
                } else if (i == 0) {
                    min = dp[i][j - 1];
                } else if (j == 0) {
                    min = dp[i - 1][j];
                } else {
                    min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
                // 2个角度的最小值或者当前的值
                dp[i][j] = Math.min(dp[i][j], min + grid[i][j]);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        var cla = new N0064_最小路径和();
        var a = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        assert cla.minPathSum(a) == 7;

        var b = new int[][]{{1, 2, 3}, {4, 5, 6}};
        assert cla.minPathSum(b) == 12;
    }
}
