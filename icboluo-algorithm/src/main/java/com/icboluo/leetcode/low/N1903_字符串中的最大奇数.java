package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-23 23:53
 */
class N1903_字符串中的最大奇数 {
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if (Character.digit(num.charAt(i), 10) % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
