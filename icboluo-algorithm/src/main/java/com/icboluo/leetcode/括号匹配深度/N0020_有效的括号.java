package com.icboluo.leetcode.括号匹配深度;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-10-31 13:27
 */
class N0020_有效的括号 {
    public static void main(String[] args) {
        N0020_有效的括号 cla = new N0020_有效的括号();
        String str = "()[]{}";
        boolean b = cla.m1(str);
        System.out.println("b = " + b);
    }

    /**
     * 仅有一种小括号
     *
     * @param str
     * @return
     */
    private boolean m1(String str) {
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                index++;
            } else {
                index--;
            }
            if (index < 0) {
                return false;
            }
        }
        return index == 0;
    }

    private boolean m2(String str) {
        // 用栈，压栈的时候进行转换，peek进行判断
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char item = str.charAt(i);
            if (item == '(' || item == '[' || item == '{') {
                stack.push(convert(item));
            }
/*            else { //2种写法一样
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != item) {
                    return false;
                }
            }*/
            else if (!stack.isEmpty() && stack.peek() == item) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private Character convert(Character item) {
        if (item == '[') {
            return ']';
        } else if (item == '{') {
            return '}';
        } else if (item == '(') {
            return ')';
        }
        return ' ';
    }
}
