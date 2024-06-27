package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @since 2024-06-27 下午11:44
 */
class N0516_最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // 这个dp的i，j代表字符串的左右索引
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
            if (dp[0][n - 1] == n) {
                return n;
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        var test = new N0516_最长回文子序列();
        System.out.println(test.longestPalindromeSubseq("bbbab"));
        // System.out.println(test.longestPalindromeSubseq("cbbd"));
        // System.out.println(test.longestPalindromeSubseq("abcba"));
        // System.out.println(test.longestPalindromeSubseq("abb"));
    }
}
