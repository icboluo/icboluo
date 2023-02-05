package com.icboluo.leetcode.括号匹配深度;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-02-05 14:03
 */
class N1614_括号的最大嵌套深度 {
    public int maxDepth(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
                max = Math.max(max, stack.size());
            } else if (s.charAt(i) == ')') {
                stack.pop();
            }
        }
        return max;
    }
}
