package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-23 22:07
 */
class N2057_值相等的最小索引 {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
