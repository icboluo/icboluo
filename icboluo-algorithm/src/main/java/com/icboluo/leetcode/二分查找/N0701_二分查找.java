package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2022-12-27 22:29
 */
class N0701_二分查找 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = target - 1;
            } else if (nums[mid] < target) {
                left = target + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
