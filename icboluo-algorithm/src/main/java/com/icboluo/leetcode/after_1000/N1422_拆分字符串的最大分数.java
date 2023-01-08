package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2023-01-07 23:39
 */

class N1422_拆分字符串的最大分数 {
    /**
     * 左字符串0的个数+右字符串1的个数的最大值
     *
     * @param s
     * @return
     */
    public int maxScore(String s) {
        // 这个max的定义略显诡异，我们认为左边的位zero，最终答案是左边的zero+右边的one，
        // 我们可以这样想，全部的1减去左边的1就是右边的1
        int max = Integer.MIN_VALUE;
        int zero = 0;
        int one = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zero++;
            } else {
                one++;
            }
            if (i != s.length() - 1) {
                // -当前左边的1
                max = Math.max(max, zero - one);
            }
        }
        return max + one;
    }
}
