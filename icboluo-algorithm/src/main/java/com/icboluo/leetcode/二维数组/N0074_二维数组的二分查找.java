package com.icboluo.leetcode.二维数组;

/**
 * @author icboluo
 * @since 2023-05-22 21:06
 */
class N0074_二维数组的二分查找 {
    public static void main(String[] args) {
        var cla = new N0074_二维数组的二分查找();
        int[][] arr = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(cla.searchMatrix(arr, 3));
        System.out.println(cla.searchMatrix(arr, 13));

        System.out.println(cla.searchMatrix(new int[][]{{1}}, 2));
        System.out.println(cla.searchMatrix(new int[][]{{1}}, 0));
        System.out.println(cla.searchMatrix(new int[][]{{1, 1}}, 2));
    }


    // 二维数组的二分查找 FIXME ERROR,怎么写都是错
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
                return true;
            }
        }
        // 这块小心越界（left-1）
        return matrix[Math.max(left - 1, 0) / matrix.length][left % matrix[0].length] == target;
    }
}
