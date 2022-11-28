package com.icboluo.leetcode.after_1000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-11-26 17:56
 */
class N1047_删除相邻重复项 {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        // stack.toString() 的顺序依然是pop顺序
        return stack.stream().map(String::valueOf).reduce("", (a, b) -> b + a);
    }
}
