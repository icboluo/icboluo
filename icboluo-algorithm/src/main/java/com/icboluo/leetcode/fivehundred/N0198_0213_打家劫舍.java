package com.icboluo.leetcode.fivehundred;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-16-22 13:16
 */
class N0198_0213_打家劫舍 {
    public int rob(int[] nums) {
        cache = new HashMap<>();
        return rob3(nums);
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

    /**
     * 迭代+2个变量，减少空间复杂度
     *
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {
        int pre = nums[0];
        int ppre = nums[0];
        if (nums.length > 1) {
            pre = Math.max(nums[0], nums[1]);
        }
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(pre, ppre + nums[i]);
            ppre = pre;
            pre = cur;
        }
        return pre;
    }

    /**
     * 0213
     *
     * @param nums
     * @return
     */
    public int rob0213(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
//        开始可以是第一个房间，也可以是第二个房间(并没有其他的可能性
        int one = robFun(nums, 0, nums.length - 1);
        int two = robFun(nums, 1, nums.length);
        return Math.max(one, two);
    }

    public int robFun(int[] nums, int start, int end) {
        int beforeTwo = nums[start];
        int dpBeforeOne = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int cur = Math.max(dpBeforeOne, beforeTwo + nums[i]);
            beforeTwo = dpBeforeOne;
            dpBeforeOne = cur;
        }
        return dpBeforeOne;
    }

    public static void main(String[] args) {
        N0198_0213_打家劫舍 cla = new N0198_0213_打家劫舍();
        int[] arr = {1, 2, 3, 1};
        int i = cla.rob3(arr);
        System.out.println("i = " + i);
    }
}
