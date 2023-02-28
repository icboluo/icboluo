package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-02-28 21:38
 */
class N1844_将所有的数字用字符替换 {
    public String replaceDigits(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                res += s.charAt(i);
            } else {
                res += Character.toString(s.charAt(i - 1) + Character.digit(s.charAt(i), 10));
            }
        }
        return res;
    }
}
