package com.icboluo.leetcode.前缀和_差分数组;

/**
 * @author icboluo
 * @since 2023-01-07 22:14
 */
class N1413_最小开始值 {
    /**
     * 开始值加上数组里面的每一个元素，每一步都必须大于1
     *
     * @param nums
     * @return
     */
    public int minStartValue(int[] nums) {
        int preSum = 0;
        // 最小前缀和，只要我们比这个大，就可以得到每一步都是对的
        int minVal = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            minVal = Math.min(minVal, preSum);
        }
        return 1 - minVal;
    }
}
