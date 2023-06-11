package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-20 22:27
 */
public class N2315_数星号 {
    // 2组| 之间的*有多少
    // 第1个和第2个是一对，34一对，求23之间的❤号
    public int countAsterisks(String s) {
        int shuGang = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                shuGang++;
            }
            if (shuGang % 2 == 0 && s.charAt(i) == '*') {
                count++;
            }
        }
        return count;
    }
}
