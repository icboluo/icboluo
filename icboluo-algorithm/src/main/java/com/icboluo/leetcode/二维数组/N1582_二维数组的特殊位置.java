package com.icboluo.leetcode.二维数组;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-02-05 13:00
 */
class N1582_二维数组的特殊位置 {
    /**
     * 当前位置为1，所有行所有列均为0
     *
     * @param mat
     * @return
     */
    public int numSpecial(int[][] mat) {
        // 对于i，j点，判断当前元素为1并且行和为1，列和为1即可
        // 行是否和为1，这两个循环可以放到一个循环中，然后bool数组改为int即可
        boolean[] row = new boolean[mat.length];
        for (int i = 0; i < mat.length; i++) {
            row[i] = Arrays.stream(mat[i]).sum() == 1;
        }
        boolean[] col = new boolean[mat[0].length];
        for (int j = 0; j < mat[0].length; j++) {
            int finalJ = j;
            col[j] = Arrays.stream(mat).mapToInt(ints -> ints[finalJ]).sum() == 1;
        }
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1 && row[i] && col[j]) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
