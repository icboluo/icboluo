package com.icboluo.leetcode.前缀和_差分数组;

/**
 * @author icboluo
 * @since 2023-10-21 20:30
 */
class N0304_二维数组区域和检索 {
    int[][] preSum;

    public N0304_二维数组区域和检索(int[][] matrix) {
        preSum = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i < preSum.length; i++) {
            for (int j = 1; j < preSum[i].length; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
    }
}
