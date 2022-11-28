package com.icboluo.leetcode.after_0100;

/**
 * @author icboluo
 * @since 2022-11-26 11:37
 */
class N0168_Excel列数字转换 {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }

    public String convertToTitle2(int n) {
        return n == 0 ? "" : convertToTitle2(--n / 26) + (char) ('A' + (n % 26));
    }
}
