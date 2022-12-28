package com.icboluo.leetcode.after_0800;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-12-28 23:05
 */
class N0931_最小下降路径和 {
    /**
     * 从第一行到最后一行的路径最小值
     * 除了能想到的回溯方法，还有其他方法吗 TODO 回溯求解
     * 可以逐步讨论，求到达每一个元素路径最小值即可
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int c = 0; c < matrix[0].length; c++) {
            dp[0][c] = matrix[0][c];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int leftUp = j == 0 ? Integer.MAX_VALUE : dp[i - 1][j - 1];
                int up = dp[i - 1][j];
                int rightUp = j == matrix[i].length - 1 ? Integer.MAX_VALUE : dp[i - 1][j + 1];
                dp[i][j] = Math.min(leftUp, up);
                dp[i][j] = Math.min(dp[i][j], rightUp);
                dp[i][j] += matrix[i][j];
            }
        }
        return Arrays.stream(dp[dp.length - 1]).min().orElse(0);
    }

    /**
     * 1289 下降路径相邻行并非同一列
     *
     * @param grid
     * @return
     */
    public int minFallingPathSum2(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int c = 0; c < grid[0].length; c++) {
            dp[0][c] = grid[0][c];
        }
        for (int i = 1; i < grid.length; i++) {
            // 每一行元素，val从小到大排序 idx：val
            PriorityQueue<int[]> idxPq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            for (int j = 0; j < dp[i - 1].length; j++) {
                idxPq.add(new int[]{j, dp[i - 1][j]});
            }
            for (int j = 0; j < grid[i].length; j++) {
                // 如果是同一列
                assert idxPq.peek() != null;
                if (idxPq.peek()[0] == j) {
                    // 先取出来 再放进去
                    int[] poll = idxPq.poll();
                    assert idxPq.peek() != null;
                    dp[i][j] = grid[i][j] + idxPq.peek()[1];
                    idxPq.add(poll);
                } else {
                    dp[i][j] = grid[i][j] + idxPq.peek()[1];
                }
            }
        }
        return Arrays.stream(dp[dp.length - 1]).min().getAsInt();
    }
}
