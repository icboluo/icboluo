package com.icboluo.leetcode.峰值低谷问题;

/**
 * @author icboluo
 * @since 2023-06-10 13:01
 */
class N0941_有效山阵 {
    // 有效山阵，先递增后递减
    // 方法1：左右2个人同时登山，判断是否相遇
    public boolean validMountainArray(int[] arr) {
        if (arr.length == 1) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        // 小心纯递增递减的边界情况
        while (left < arr.length - 2 && arr[left + 1] > arr[left]) {
            left++;
        }
        while (right > 1 && arr[right - 1] > arr[right]) {
            right--;
        }
        return left == right;
    }
}

