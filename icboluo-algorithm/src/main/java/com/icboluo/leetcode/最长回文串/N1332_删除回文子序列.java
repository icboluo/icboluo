package com.icboluo.leetcode.最长回文串;

/**
 * @author icboluo
 * @since 2023-01-08 0:33
 */
class N1332_删除回文子序列 {
    /**
     * 题目限制了只能由a和b组成，a和b组成只有2种情况，1种是回文序列+回文序列，一种是一个回文序列
     *
     * @param s
     * @return
     */
    public int removePalindromeSub(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.equals(new StringBuilder(s).reverse().toString())) {
            return 1;
        }
        return 2;
    }
}
