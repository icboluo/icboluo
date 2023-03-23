package com.icboluo.leetcode.二维数组;

import com.icboluo.util.ArrayHelper;

/**
 * @author icboluo
 * @since 2023-03-23 23:03
 */
class N2022_将一维数组转换为二维数组 {
    public static void main(String[] args) {
        var cla = new N2022_将一维数组转换为二维数组();
        int[][] arr = cla.construct2DArray(new int[]{1, 1, 1, 1}, 4, 1);
        ArrayHelper.print(arr);
    }

    /**
     * FIXME ERROR
     * @param original
     * @param m
     * @param n
     * @return
     */
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][0];
        }
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = original[m * i + j];
            }
        }
        return arr;
    }
}
