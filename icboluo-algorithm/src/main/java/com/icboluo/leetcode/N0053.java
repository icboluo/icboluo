package com.icboluo.leetcode;

/**
 * @author icboluo
 * @date 2021-41-18 12:41
 */
public class N0053 {
    public int maxSubArray(int[] nums) {
//        定义i被选择时候的最大值
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int j : dp) {
            max = Math.max(max, j);
        }
        return max;
     }
}
