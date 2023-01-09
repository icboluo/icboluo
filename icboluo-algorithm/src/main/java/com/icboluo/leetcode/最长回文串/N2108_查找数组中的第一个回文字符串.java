package com.icboluo.leetcode.最长回文串;

/**
 * @author icboluo
 * @since 2022-12-30 0:34
 */
class N2108_查找数组中的第一个回文字符串 {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }
        return "";
    }

    private boolean isPalindrome(String str) {
        int l = 0;
        int r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l++) != str.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
