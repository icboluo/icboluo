package com.icboluo.leetcode.股票买卖;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-12-30 1:11
 */
class N2016_最大差值 {
    /**
     * 寻找 i<j &&arr[i]<arr[j] 的最大差值，简称下一个最大值
     * 我们不能通过单调递增栈来解决这个问题，因为单调递增栈求的是下一个较大值，而不是最大值
     *
     * @param nums
     * @return
     */
    public int maximumDifference1(int[] nums) {
        int max = -1;
        // 这个没有用到
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                Integer idx = stack.pop();
                res[idx] = i;
                max = Math.max(max, nums[i] - nums[idx]);
            }
            stack.push(i);
        }
        return max;
    }

    public int maximumDifference(int[] nums) {
        // 最大差值
        int max = -1;
        // 前面的最小值
        int preMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 因为是递增，我们总需要后面的元素减去前面的元素
            max = Math.max(max, nums[i] - preMin);
            preMin = Math.min(preMin, nums[i]);
        }
        // 如果有相等的数其实也是失败，这里的判断相当于 if 0||-1, return -1
        return max == 0 ? -1 : max;
    }
}
