package com.icboluo.leetcode.after_1000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-01-07 16:29
 */
class N1464_数组中2个元素的最大乘积 {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }
}
