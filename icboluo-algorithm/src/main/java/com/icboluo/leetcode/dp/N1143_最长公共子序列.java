package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @since 2023-09-04 22:16
 */
class N1143_最长公共子序列 {
    public static void main(String[] args) {
        var cla = new N1143_最长公共子序列();
        System.out.println(cla.longestCommonSubsequence("abcde", "ace"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    // 既然不等于的话，就算作上一个最优即可
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
