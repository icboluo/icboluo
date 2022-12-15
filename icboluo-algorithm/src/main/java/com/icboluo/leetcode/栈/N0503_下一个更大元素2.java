package com.icboluo.leetcode.栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-12-15 22:41
 */
class N0503_下一个更大元素2 {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        // 存储索引
        Deque<Integer> stack = new ArrayDeque<>();
        // 环形数组我们不如直接*2就可以假定暂时的环形
        for (int i = 0; i < nums.length * 2; i++) {
            int cur = nums[i % nums.length];
            // 如果栈中的元素比较小，说明找到第一个想近的结果了
            while (!stack.isEmpty() && nums[stack.peek()] < cur) {
                Integer pop = stack.pop();
                res[pop] = cur;
            }
            stack.push(i % nums.length);
        }
        return res;
    }
}
