package com.icboluo.leetcode;

/**
 * @author icboluo
 * @date 2021-27-28 13:27
 */
public class N0213_打家劫舍 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
//        开始可以是第一个房间，也可以是第二个房间
        int one = robFun(nums, 0, nums.length - 1);
        int two = robFun(nums, 1, nums.length);
        return Math.max(one, two);
    }

    public int robFun(int[] nums, int start, int end) {
        int beforeTwo = nums[start];
        int beforeOne = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int cur = Math.max(beforeOne, beforeTwo + nums[i]);
            beforeTwo = beforeOne;
            beforeOne = cur;
        }
        return beforeOne;
    }
}
