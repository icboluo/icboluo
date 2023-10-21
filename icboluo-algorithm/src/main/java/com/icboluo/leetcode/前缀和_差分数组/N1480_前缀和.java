package com.icboluo.leetcode.前缀和_差分数组;

/**
 * @author icboluo
 * @since 2023-01-07 16:19
 */
class N1480_前缀和 {
    public int[] runningSum(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }
        return arr;
    }
}
