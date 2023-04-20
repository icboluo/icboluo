package com.icboluo.leetcode.二维数组;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-04-21 0:12
 */
class N2500_删除每行的最大值 {
    // 似乎排序可以解决这个问题
    public int deleteGreatestValue(int[][] grid) {
        for (int[] arr : grid) {
            Arrays.sort(arr);
        }
        int res = 0;
        for (int j = grid[0].length - 1; j >= 0; j--) {
            int finalJ = j;
            int max = Arrays.stream(grid).mapToInt(arr -> arr[finalJ]).max().orElse(0);
            res += max;
        }
        return res;
    }
}
