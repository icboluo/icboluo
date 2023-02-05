package com.icboluo.leetcode.二维数组;

/**
 * @author icboluo
 * @since 2023-02-05 13:10
 */
class N2482_行和列中1和0的差值 {
    public int[][] onesMinusZeros(int[][] grid) {
        int[] row = new int[grid.length];
        int[] col = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                row[i] += grid[i][j];
                col[j] += grid[i][j];
            }
        }
        int[][] arr = new int[grid.length][grid[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // 1的数目-0的数目=1的数目-（all-1的数目）
                arr[i][j] = 2 * row[i] + 2 * col[j] - grid.length - grid[i].length;
            }
        }
        return arr;
    }
}
