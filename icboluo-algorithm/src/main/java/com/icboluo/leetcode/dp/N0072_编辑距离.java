package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @since 2023-05-16 21:01
 */
class N0072_编辑距离 {
    public static void main(String[] args) {
        var cla = new N0072_编辑距离();
        System.out.println(cla.minDistance("", "a"));
    }

    // 超时,很不错的动态规划
    public int minDistance1(String word1, String word2) {
        if (word1.equals("") && word2.equals("")) {
            return 0;
        }
        if (word1.equals("")) {
            return word2.length();
        }
        if (word2.equals("")) {
            return word1.length();
        }
        if (word1.charAt(0) == word2.charAt(0)) {
            return minDistance1(word1.substring(1), word2.substring(1));
        }
        // 这个对于字符串1而言需要增加
        int insert = 1 + minDistance1(word1, word2.substring(1));
        int delete = 1 + minDistance1(word1.substring(1), word2);
        int replace = 1 + minDistance1(word1.substring(1), word2.substring(1));
        return Math.min(Math.min(insert, delete), replace);
    }

    public int minDistance(String word1, String word2) {
        // dp[i][0] 代表从word1 i到word2 0需要的步骤数
        // 对于 sabc abcd 而言，在0索引的时候，删除s索引的总体时间会比较低，所以会走删除的子逻辑
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // 初始化dp
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < dp.length - 1; i++) {
            for (int j = 0; j < dp[i].length - 1; j++) {
                // 如果当前元素相等，则下一个对角点记录本次结果
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    // 如果不相似，则当前元素要相等，我们有3中策略可选，但是每种都是+1
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j], dp[i + 1][j]), dp[i][j + 1]) + 1;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
