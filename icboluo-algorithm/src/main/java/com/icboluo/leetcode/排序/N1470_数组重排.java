package com.icboluo.leetcode.排序;

/**
 * @author icboluo
 * @since 2023-01-07 16:26
 */
class N1470_数组重排 {
    /**
     * 前一半和后一半交叉处理
     *
     * @param nums
     * @param n
     * @return
     */
    public int[] shuffle(int[] nums, int n) {
        int[] arr = new int[nums.length];
        int left = 0;
        int right = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = nums[left++];
            } else {
                arr[i] = nums[right++];
            }
        }
        return arr;
    }
}
