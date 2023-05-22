package com.icboluo.leetcode.after_0000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-05-22 21:13
 */
class N0150_逆波兰表达式求值 {
    // 代码比较简单，原理不懂
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String cur : tokens) {
            if (cur.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (cur.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (cur.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (cur.equals("/")) {
                Integer a = stack.pop();
                Integer b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(cur));
            }
        }
        return stack.pop();
    }
}
