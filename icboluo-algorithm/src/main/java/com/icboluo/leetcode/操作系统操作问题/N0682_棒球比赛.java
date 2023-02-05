package com.icboluo.leetcode.操作系统操作问题;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-02-05 13:52
 */
class N0682_棒球比赛 {
    public int calPoints(String[] operations) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String operation : operations) {
            if ("C".equals(operation)) {
                stack.pop();
            } else if ("D".equals(operation)) {
                assert !stack.isEmpty();
                stack.push(stack.peek() * 2);
            } else if ("+".equals(operation)) {
                Integer pre = stack.pop();
                assert !stack.isEmpty();
                int cur = stack.peek() + pre;
                stack.push(pre);
                stack.push(cur);
            } else {
                stack.push(Integer.parseInt(operation));
            }
        }
        return stack.stream().mapToInt(Integer::intValue).sum();
    }
}
