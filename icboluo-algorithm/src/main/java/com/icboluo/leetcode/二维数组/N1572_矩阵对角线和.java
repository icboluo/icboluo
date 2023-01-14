package com.icboluo.leetcode.二维数组;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-01-13 19:04
 */
class N1572_矩阵对角线和 {
    public static void main(String[] args) {
        var cla = new N1572_矩阵对角线和();
        @SuppressWarnings("all") int[][] arr = {// -------------
                {1, 2, 3},// ------------------
                {4, 5, 6},// ---------------------
                {7, 8, 9},
        };
        int i = cla.diagonalSum(arr);
        System.out.println("i = " + i);
    }

    public int diagonalSum(int[][] mat) {
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (i == j) {
                    sum += mat[i][j];
                } else if (i + j == mat[i].length - 1) {
                    sum += mat[i][j];
                }
            }
        }
        return sum;
    }

    /**
     * 2133 检查是否每一行每一列都包含全部整数 FIXME ERROR
     *
     * @param matrix
     * @return
     */
    public boolean checkValid(int[][] matrix) {
        Set<Integer> set = Arrays.stream(matrix[0]).boxed().collect(Collectors.toSet());
        // 行不变，列变
        for (int i = 1; i < matrix.length; i++) {
            Set<Integer> temp = Arrays.stream(matrix[i]).boxed().collect(Collectors.toSet());
            if (!temp.equals(set)) {
                return false;
            }
        }
        // 列不变，行变
        for (int j = 1; j < matrix[0].length; j++) {
            int finalJ = j;
            Set<Integer> temp = Arrays.stream(matrix).map(arr -> arr[finalJ]).collect(Collectors.toSet());
            if (!temp.equals(set)) {
                return false;
            }
        }
        return true;
    }
}
