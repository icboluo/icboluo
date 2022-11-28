package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-11-25 16:08
 */
class N0014_最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            char temp = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (temp != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
