package com.icboluo.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-05-16 21:10
 */
class N0032_最长有效括号 {
    public int longestValidParentheses(String s) {
        int max = 0;
        // 栈中存储不合法括号索引
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    if (s.charAt(stack.peek()) == '(') {
                        stack.pop();
                    } else {
                        stack.push(i);
                    }
                    // 也可以在这里即使计算结果 当前索引到上一个不合法索引的距离就是最长距离
                    if (stack.isEmpty()) {
                        // 起始位置
                        max = i + 1;
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        // if (stack.isEmpty()) {
        //     return s.length();
        // }
        // int right = s.length();
        // while (!stack.isEmpty()) {
        //     int pop = stack.pop();
        //     max = Math.max(max, right - pop - 1);
        //     right = pop;
        // }
        // max = Math.max(max, right);
        return max;
    }
}
