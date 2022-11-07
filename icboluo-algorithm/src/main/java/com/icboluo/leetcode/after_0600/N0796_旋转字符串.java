package com.icboluo.leetcode.after_0600;

/**
 * @author icboluo
 * @since 2022-11-07 23:01
 */
 class N0796_旋转字符串 {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
