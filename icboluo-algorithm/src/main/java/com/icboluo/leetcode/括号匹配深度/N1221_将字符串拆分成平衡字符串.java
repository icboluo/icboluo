package com.icboluo.leetcode.括号匹配深度;

/**
 * @author icboluo
 * @since 2022-12-28 22:10
 */
class N1221_将字符串拆分成平衡字符串 {
    public int balancedStringSplit(String s) {
        int count = 0;
        int temp = 0;
        // 成对出现的，类似于括号匹配
        for (int i = 0; i < s.length(); i++) {
            if ('L' == s.charAt(i)) {
                temp++;
            }else {
                temp--;
            }
            if (temp == 0) {
                count++;
            }
        }
        return count;
    }
}
