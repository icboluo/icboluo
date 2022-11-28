package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-25 11:48
 */
class N0867_转置 {
    public int[][] transpose(int[][] matrix) {
        int[][] arr = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                arr[j][i] = matrix[i][j];
            }
        }
        return arr;
    }
}
