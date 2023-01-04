package com.icboluo.leetcode.最长回文串;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-01-04 19:22
 */
class N2454_下2个更大的元素 {
    /**
     * TODO hard not understand
     *
     * @param nums
     * @return
     */
    public int[] secondGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        Deque<Integer> temp = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // stack2 存储下2个更大的元素
            while (!stack2.isEmpty() && nums[stack2.peek()] < nums[i]) {
                res[stack2.pop()] = nums[i];
            }
            // stack1 存储下一个较大的元素
            while (!stack1.isEmpty() && nums[stack1.peek()] < nums[i]) {
                temp.push(stack1.pop());
            }
            while (!temp.isEmpty()) {
                stack2.push(temp.pop());
            }
            stack1.push(i);
        }
        return res;
    }
}
