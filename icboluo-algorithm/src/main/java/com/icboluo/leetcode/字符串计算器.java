package com.icboluo.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2022-08-25 20:53
 */
class 字符串计算器 {
    public static void main(String[] args) {
        var cla = new 字符串计算器();
        String str = "3*(4-5/2)-6";
        int ans = cla.cal(str, 0);
        System.out.println(ans);
    }

    private int addSub(String str) {
        int num = 0;
        int symbol = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (!Character.isDigit(cur) || i == str.length() - 1) {
                if (symbol == '+') {
                    stack.push(num);
                } else if (symbol == '-') {
                    stack.push(-num);
                }
                symbol = cur;
                num = 0;
            }
        }
        return stack.stream().reduce(0, Integer::sum);
    }

    /**
     * 括号内的字符串向前走的时候，原来的递归上层索引是没有动的，期望上层的索引跟着下层的一起动
     */
    private int endIdx;

    private int cal(String str, int start) {
        str = str.replace(" ", "");
        int num = 0;
        int symbol = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = start; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (cur == '(') {
                num = cal(str, i + 1);
                i = endIdx;
            }
            if (!Character.isDigit(cur) || i == str.length() - 1) {
                if (symbol == '+') {
                    stack.push(num);
                } else if (symbol == '-') {
                    stack.push(-num);
                } else if (symbol == '*') {
                    Integer pre = stack.pop();
                    stack.push(pre * num);
                } else if (symbol == '/') {
                    Integer pre = stack.pop();
                    assert num != 0;
                    stack.push(pre / num);
                }
                symbol = cur;
                num = 0;
            }
            // 必须放在这块，遇到大括号的时候，需要先把前面的运算完成
            if (cur == ')') {
                endIdx = i;
                break;
            }
        }
        return stack.stream().reduce(0, Integer::sum);
    }
}
