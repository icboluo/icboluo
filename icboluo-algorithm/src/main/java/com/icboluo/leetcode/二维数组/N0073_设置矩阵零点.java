package com.icboluo.leetcode.二维数组;

/**
 * @author icboluo
 * @since 2023-05-16 21:08
 */
class N0073_设置矩阵零点 {
    // 说实话，这样写代码特别吃力
    // 方法1,1次遍历，收集0点坐标，第二次遍历直接赋值
    // 方法2，第0行|0列存储状态，状态值移动到最上或者最左，因为反转如果是0，也会影响到最上，最左
    public void setZeroes(int[][] matrix) {
        // 第一列的元素是否为0,1代表false,现有的左上角代表第一行元素是否为0而已
        int a = 1;
        // 将第一列是否为0存储下来，然后把下面的0元素浮到第一列
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                a = 0;
            }
            for (int j = 1; j < matrix[i].length; j++) {
                // 首先，如果元素为0,我们应该将于元素行列为0标记两边
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 这块正向循环会首先更新第一行，但是我们第一行存储了值，应该最后被更新，所以是不合理的
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 1; j--) {
                // for (int i = 0; i < matrix.length; i++) {
                //     for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 第一列如果为0，则全部置为0
        if (a == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
