package com.icboluo.leetcode.after_1300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2023-02-28 20:53
 */
class N1694_重新格式化电话号码 {
    public static void main(String[] args) {
        var cla = new N1694_重新格式化电话号码();
        System.out.println(cla.reformatNumber("1-23-45 6"));
    }

    // error 这种解法是困难的
    // 出错原因，不应该在第一次遍历的时候直接收集到res中，也不应该拼接-增加复杂度
    // 不应该根据lastIndexOf+res.len混淆代码逻辑，其实只想知道剩余字符是几个，使用清楚的数据更加简单一点
    public String reformatNumber1(String number) {
        String res = "";
        int item = 0;
        for (int i = 0; i < number.length(); i++) {
            if (item == 3) {
                item = 0;
                res += "-";
            }
            char ch = number.charAt(i);
            if (!Character.isDigit(ch)) {
                continue;
            }
            item++;
            res += "" + ch;
        }
        int lastIndexOf = res.lastIndexOf("-");
        // 3+1位
        if (lastIndexOf == res.length() - 2) {
        }
        return res;
    }

    public String reformatNumber(String number) {
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < number.length(); i++) {
            if (Character.isDigit(number.charAt(i))) {
                queue.add(number.charAt(i));
            }
        }
        String res = "";
        while (queue.size() > 4) {
            // 前置的空字符串是有必要的，防止进行了char类型的运算
            res += "" + queue.poll() + queue.poll() + queue.poll() + "-";
        }
        if (queue.size() == 4) {
            res += "" + queue.poll() + queue.poll() + "-";
        }
        while (!queue.isEmpty()) {
            res += queue.poll();
        }
        return res;
    }
}
