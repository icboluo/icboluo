package com.icboluo.leetcode.二维数组;

/**
 * @author icboluo
 * @since 2023-05-22 21:06
 */
class N0074_二维数组的二分查找 {
    // 二维数组的二分查找 FIXME ERROR
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int midVal = matrix[mid / matrix.length][mid % matrix[0].length];
            // [mid+1,right]
            if (midVal < target) {
                left = mid + 1;
            } else if (midVal > target) {
                right = mid - 1;
            } else if (midVal == target) {
                // 其实这块return true就可以了啊
                right = mid - 1;
                return true;
            }
        }
        // 这块小心越界（left-1）
        return matrix[left - 1 / matrix.length][left % matrix[0].length] == target;
    }
}
