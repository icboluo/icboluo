package com.icboluo.leetcode.栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-12-30 1:11
 */
class N2016_最大差值 {
    /**
     * 寻找 i<j &&arr[i]<arr[j] 的最大差值，简称下一个最大值 FIXME ERROR
     *
     * @param nums
     * @return
     */
    public int maximumDifference(int[] nums) {
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
}
