package com.icboluo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-16-22 13:16
 */
public class N0198_打家劫舍 {
    public int rob(int[] nums) {
        cache = new HashMap<>();
        return robFun(nums, 0);
    }

    private Map<Integer, Integer> cache;

    public int robFun(int[] nums, int left) {
        if (cache.containsKey(left)) {
            return cache.get(left);
        }
        if (left == nums.length - 1) {
            return nums[nums.length - 1];
        }
        if (left == nums.length - 2) {
            return Math.max(nums[nums.length - 2], nums[nums.length - 1]);
        }
//  抢，下个房间不能抢
        int rob = robFun(nums, left + 2);
//        不抢
        int notRob = robFun(nums, left + 1);
        int max = Math.max(rob + nums[left], notRob);
        cache.put(left, max);
        return max;
    }

    public int rob2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length > 1) {
            dp[1] = Math.max(nums[0], nums[1]);
        }
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
