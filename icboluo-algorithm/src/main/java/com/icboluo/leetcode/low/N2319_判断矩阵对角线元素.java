package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-04-20 22:34
 */
class N2319_判断矩阵对角线元素 {
    /**
     * 矩阵对角线元素不为0，非对角线元素为0
     *
     * @param grid
     * @return
     */
    public boolean checkXMatrix(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == j || i == grid.length - j - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
