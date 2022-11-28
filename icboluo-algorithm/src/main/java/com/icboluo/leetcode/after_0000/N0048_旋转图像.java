package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-11-25 16:35
 */
class N0048_旋转图像 {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        // 针对00点进行交换
        for (int i = 0; i < (matrix.length + 1) / 2; i++) {
            // 一个加一个不加，保证只选择一次
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];// 00
                matrix[i][j] = matrix[length - j - 1][i];// 00=20
                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];// 20=22
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];// 00=20
                matrix[j][length - i - 1] = temp;// 02=00
            }
        }
    }
}
