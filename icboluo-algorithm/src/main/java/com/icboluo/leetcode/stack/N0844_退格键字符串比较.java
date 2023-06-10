package com.icboluo.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-06-10 12:58
 */
class N0844_退格键字符串比较 {
    public boolean backspaceCompare(String s, String t) {
        Deque<Character> a = toStack(s);
        Deque<Character> b = toStack(t);
        // 怎么判断2个栈是否相等：禁止直接equals
        return a.toString().equals(b.toString());
    }

    private Deque<Character> toStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) == '#') {
                stack.pop();
            } else if (s.charAt(i) != '#') {
                stack.push(s.charAt(i));
            }
        }
        return stack;
    }
}
