package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-23 23:15
 */
 class N2000_反转字符串前缀 {
    public String reversePrefix(String word, char ch) {
        String res = "";
        for (int i = 0; i < word.length(); i++) {
            res = word.charAt(i) + res;
            if (ch == word.charAt(i)) {
                res += word.substring(i + 1);
                return res;
            }
        }
        return word;
    }
}
