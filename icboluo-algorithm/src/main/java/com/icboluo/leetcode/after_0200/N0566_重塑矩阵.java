package com.icboluo.leetcode.after_0200;

import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-02 12:44
 */
class N0566_重塑矩阵 {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int g = mat.length;
        int k = mat[0].length;
        if (g * k != r * c) {
            return mat;
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                list.add(mat[i][j]);
            }
        }
        int[][] arr = new int[r][c];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = list.get(idx++);
            }
        }
        return arr;
    }
}
