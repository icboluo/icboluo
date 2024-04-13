package com.icboluo.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-09-17 23:52
 */
public class N0856_括号得分 {
    // (()()()) = (()) + (()) + (()) 存在这样的推论，评论区里面有答案，有点复杂
    // 括号得分 复杂栈解法
    public int scoreOfParentheses1(String s) {
        // 栈里面存储的是每一个左括号以前的得分
        Deque<Integer> stack = new ArrayDeque<>();
        // 存储的是当前的得分
        int cur = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(cur);
                cur = 0;
            } else {
                Integer pop = stack.pop();
                cur = pop + Math.max(cur * 2, 1);
            }
        }
        return cur;
    }

    // 简单解法
    public int scoreOfParentheses2(String s) {
        int score = 0;
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            // 这个需要放到前面，预先增加
            if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                score += (int) Math.pow(2, depth);
            }
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
            }

        }
        return score;
    }
}
