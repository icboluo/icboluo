package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-02-28 22:05
 */
class N1848_到目标元素的最小距离 {
    public int getMinDistance(int[] nums, int target, int start) {
        for (int i = 0; i < nums.length; i++) {
            if ((start + i < nums.length && nums[start + i] == target) || (start - i >= 0 && nums[start - i] == target)) {
                return i;
            }
        }
        return -1;
    }
}
