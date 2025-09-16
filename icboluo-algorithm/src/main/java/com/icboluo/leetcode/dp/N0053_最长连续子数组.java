package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @since 2021-41-18 12:41
 */
class N0053_最长连续子数组 {
    public static void main(String[] args) {
        var cla = new N0053_最长连续子数组();
        System.out.println(cla.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(cla.maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    public int maxSubArray(int[] nums) {
        // 定义i被选择时候的最大值
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int j : dp) {
            max = Math.max(max, j);
        }
        return max;
    }
}
