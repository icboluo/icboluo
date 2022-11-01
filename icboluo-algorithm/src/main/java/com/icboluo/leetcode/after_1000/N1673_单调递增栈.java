package com.icboluo.leetcode.after_1000;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-11-01 13:24
 */
class N1673_单调递增栈 {
    public static void main(String[] args) {
        N1673_单调递增栈 cla = new N1673_单调递增栈();
//        int[] ints = cla.mostCompetitive(new int[]{3, 5, 2, 6,}, 2);
        int[] ints = cla.mostCompetitive(new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4);
        System.out.println(Arrays.toString(ints));
    }

    private int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // 在一次遍历过程中减到位，不要去操作栈中的元素，增加负荷
            while (!stack.isEmpty() && nums[i] < stack.peek() && stack.size() + nums.length - i > k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
