package com.icboluo.leetcode.after_0800;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-12-29 0:00
 */
class N0908_最小范围 {
    /**
     * 你可以对数组中的元素进行加 -k->k之间的任意数
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeI(int[] nums, int k) {
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length - 1];
        return Math.max(0, max - min - 2 * k);
    }

    /**
     * 0910 最小范围2
     * it`s hard to understand
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeII(int[] nums, int k) {
        return -1;
    }
}
