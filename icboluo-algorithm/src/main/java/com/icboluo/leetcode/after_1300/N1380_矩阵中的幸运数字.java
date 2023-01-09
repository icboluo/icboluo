package com.icboluo.leetcode.after_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-07 22:56
 */
class N1380_矩阵中的幸运数字 {
    /**
     * 行最小列最大的元素
     * 因为要同时满足这2个条件，所以定义2个条件求和即可
     *
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        int[] rowMin = new int[matrix.length];
        int[] colMax = new int[matrix[0].length];
        // 最小值需要fill，否则怎么算都是0
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rowMin[i] == colMax[j]) {
                    res.add(matrix[i][j]);
                    // 这个break需要好好研究下，没看得懂
                    break;
                }
            }
        }
        return res;
    }
}
