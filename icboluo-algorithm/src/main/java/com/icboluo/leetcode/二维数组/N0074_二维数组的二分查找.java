package com.icboluo.leetcode.二维数组;

import org.junit.jupiter.api.Assertions;

/**
 * @author icboluo
 * @since 2023-05-22 21:06
 */
class N0074_二维数组的二分查找 {
    public static void main(String[] args) {
        var cla = new N0074_二维数组的二分查找();
        int[][] arr = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        Assertions.assertTrue(cla.searchMatrix(arr, 3));
        Assertions.assertFalse(cla.searchMatrix(arr, 13));

        Assertions.assertFalse(cla.searchMatrix(new int[][]{{1}}, 2));
        Assertions.assertFalse(cla.searchMatrix(new int[][]{{1}}, 0));
        Assertions.assertTrue(cla.searchMatrix(new int[][]{{1}}, 1));

        Assertions.assertFalse(cla.searchMatrix(new int[][]{{1, 1}}, 2));
        Assertions.assertTrue(cla.searchMatrix(new int[][]{{1, 2}}, 2));
        Assertions.assertTrue(cla.searchMatrix(new int[][]{{1, 2}}, 1));
    }


    // 二维数组的二分查找
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int midVal = matrix[mid / matrix[0].length][mid % matrix[0].length];
            // [mid+1,right]
            if (midVal < target) {
                left = mid + 1;
            } else if (midVal > target) {
                right = mid - 1;
            } else if (midVal == target) {
                return true;
            }
        }
        // 找到最后都没有找到
        return false;
    }
}
