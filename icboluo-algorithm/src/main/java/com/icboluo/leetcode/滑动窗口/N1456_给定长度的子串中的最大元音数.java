package com.icboluo.leetcode.滑动窗口;

import com.icboluo.constant.CharConstant;

/**
 * @author icboluo
 * @since 2023-01-15 16:53
 */
class N1456_给定长度的子串中的最大元音数 {

    /**
     * 长度为k的子串，中元音字母数量最大是多少
     *
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        int l = 0;
        int r = 0;
        int max = 0;
        int winCount = 0;
        while (r < s.length()) {
            char right = s.charAt(r++);
            if (CharConstant.VOWELS_LIST.contains(right)) {
                winCount++;
            }
            max = Math.max(max, winCount);
            if (r - l == k) {
                if (CharConstant.VOWELS_LIST.contains(s.charAt(l++))) {
                    winCount--;
                }
            }
        }
        return max;
    }
}
