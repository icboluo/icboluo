package com.icboluo.leetcode.after_0600;

/**
 * @author icboluo
 * @since 2023-09-15 18:50
 */
class N0678_有效的括号字符串 {
    public boolean checkValidString(String s) {
        // 我们给左括号和右括号给一个区间
        int max = 0;
        int min = 0;
        for (char ch : s.toCharArray()) {
            // 策略：左括号加右减
            if (ch == '(') {
                max++;
                min++;
            } else if (ch == ')') {
                max--;
                min--;
            } else if (ch == '*') {
                max++;
                min--;
            }
            // 每次循环的时候，都要保证起码的左括号不能太少
            if (max < 0) {
                return false;
            }
            // 如果最大值合格，就要将最小值也做合格，如果是负值，说明右括号太多了，丢弃掉当前的*即可，此块非常难理解 hard
            // 左括号如果过少，将当前字符认为是*号抛弃
            min = Math.max(min, 0);
        }
        return min == 0;
    }
}
