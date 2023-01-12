package com.icboluo.leetcode.二维数组;

/**
 * @author icboluo
 * @since 2022-11-07 23:24
 */
class N0766_对角线相等的矩阵 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
