package com.icboluo.leetcode.animation.zero;

import com.icboluo.util.IcBoLuoException;

import java.util.Stack;

/**
 * @author icboluo
 * @date 2020-10-09 20:47
 */
public class _0020_有效的括号 {
    public static void main(String[] args) {
//        用栈，压栈的时候进行转换，peek进行判断
        String str = "()[]{}";
        boolean b = m(str);
        System.out.println(b);
    }

    private static boolean m(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                char v = convert(c);
                stack.push(v);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (pop != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static char convert(char c) {
        if (c == '[') {
            return ']';
        } else if (c == '{') {
            return '}';
        } else if (c == '(') {
            return ')';
        }
        throw new IcBoLuoException();
    }
}
