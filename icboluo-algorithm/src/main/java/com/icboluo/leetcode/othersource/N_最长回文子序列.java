package com.icboluo.leetcode.othersource;

/**
 * @author icboluo
 * @since 2024-03-06 22:48
 */
class N_最长回文子序列 {
    public static void main(String[] args) {
        System.out.println(m4("axbdbai"));
    }

    /**
     * 这个要做图搞
     * j  a x b d b a i
     * a 1 1 1 1 3
     * x   1 1 1 1 1
     * b     1 1 3 3
     * d       1 1 1
     * b         1 1
     * a           1
     * 2-4;i-1 j+1.+2;i-1|j+1
     * len-0
     * i-len
     * <p>
     * 最长回文子序列
     *
     * @param str
     * @return
     */
    private static int m4(String str) {
        int len = str.length();
        int[][] dp = new int[len][len];
        for (int i = len; i > 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
