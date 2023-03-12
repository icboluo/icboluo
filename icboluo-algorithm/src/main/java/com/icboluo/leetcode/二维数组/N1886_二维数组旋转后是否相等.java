package com.icboluo.leetcode.二维数组;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-08 20:07
 */
public class N1886_二维数组旋转后是否相等 {
    // 一个数组旋转50度和另一个 数组是否相等,二维数组的旋转还是要举例子
    public boolean findRotation(int[][] mat, int[][] target) {
        int row = mat.length;
        int col = mat[0].length;
        if (row != col) {
            return false;
        }
        int[][] arr90 = rotate(mat);
        int[][] arr180 = rotate(arr90);
        int[][] arr270 = rotate(arr180);
        return Arrays.deepEquals(mat, target) || Arrays.deepEquals(arr90, target) || Arrays.deepEquals(arr180, target) || Arrays.deepEquals(arr270, target);
    }

    private int[][] rotate(int[][] arr) {
        int[][] res = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // res 0 0=arr 2 0
                // res 0 1=arr 1 0
                // res 0 2=arr 0 0
                res[i][j] = arr[arr.length - 1 - j][i];
            }
        }
        return res;
    }
}
