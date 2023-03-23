package com.icboluo.leetcode.after_1700;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-23 23:51
 */
class N1913_2对数之间的最大乘积差 {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int a = nums[0] * nums[1];
        int b = nums[nums.length - 1] * nums[nums.length - 2];
        return b - a;
    }
}
