package com.icboluo.leetcode.after_1000;

import java.util.ArrayDeque;

/**
 * @author icboluo
 * @since 2022-11-22 12:48
 */
class N1544_使字符串变好 {
    public String makeGood(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && Math.abs(stack.peek() - s.charAt(i)) == 32) {
                stack.pop();
            }else{
                stack.push(s.charAt(i));
            }
        }
        // 简简单单的栈数组浅拷贝
        char[] res = new char[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            res[idx--] = stack.pop();
        }
        return new String(res);
    }
}
