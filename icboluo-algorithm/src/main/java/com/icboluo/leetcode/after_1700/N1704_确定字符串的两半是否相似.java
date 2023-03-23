package com.icboluo.leetcode.after_1700;

import static com.icboluo.constant.CharConstant.VOWELS_LIST;

/**
 * @author icboluo
 * @since 2023-03-24 0:34
 */
class N1704_确定字符串的两半是否相似 {
    /**
     * 元音个数是否相等
     *
     * @param s
     * @return
     */
    public boolean halvesAreAlike(String s) {
        int count = 0;
        for (int i = 0; i < s.length() / 2; i++) {
            if (VOWELS_LIST.contains(s.charAt(i))) {
                count++;
            }
            if (VOWELS_LIST.contains(s.charAt(s.length() - i - 1))) {
                count--;
            }
        }
        return count == 0;
    }

}
