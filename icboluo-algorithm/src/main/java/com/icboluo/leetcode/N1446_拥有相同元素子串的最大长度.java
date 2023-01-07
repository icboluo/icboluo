package com.icboluo.leetcode;

/**
 * @author icboluo
 * @since 2023-01-07 16:58
 */
class N1446_拥有相同元素子串的最大长度 {
    public int maxPower(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                right++;
                max = Math.max(max, right - left);
            } else {
                left = right;
                right++;
            }
        }
        return max;
    }
}
