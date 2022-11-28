package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-25 10:54
 */
class N0896_是否单调 {
    public boolean isMonotonic(int[] nums) {
        boolean isIncr = true;
        boolean isDecr = true;
        for (int i = 1; i < nums.length; i++) {
            int pre = nums[i - 1];
            int cur = nums[i];
            isIncr &= cur - pre >= 0;
            isDecr &= cur - pre <= 0;
        }
        return isIncr || isDecr;
    }

    public boolean isMonotonic2(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            int pre = nums[i - 1];
            int cur = nums[i];
            int next = nums[i + 1];
            // 这种解法是错误的，连续判断，只能用于严格单调的函数
            if ((pre - cur) * (cur - next) < 0) {
                return false;
            }
        }
        return true;
    }

    // 2210 统计高峰山谷个数
    public int countHillValley(int[] nums) {
        int count = 0;
        // left指的是上一个不同的元素
        int left = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            int cur = nums[i];
            int next = nums[i + 1];
            if ((left - cur) * (cur - next) < 0) {
                count++;
                left = cur;
            }
        }
        return count;
    }
}
