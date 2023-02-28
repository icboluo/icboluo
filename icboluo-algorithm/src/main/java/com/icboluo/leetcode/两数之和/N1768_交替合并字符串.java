package com.icboluo.leetcode.两数之和;

/**
 * @author icboluo
 * @since 2023-02-21 21:35
 */
class N1768_交替合并字符串 {
    // 合并2个字符串
    public String mergeAlternately(String word1, String word2) {
        String res = "";
        for (int i = 0; i < Math.max(word1.length(), word2.length()); i++) {
            String ch1 = i >= word1.length() ? "" : word1.charAt(i) + "";
            String ch2 = i >= word2.length() ? "" : word2.charAt(i) + "";
            res += ch1 + ch2;
        }
        return res;
    }
}
