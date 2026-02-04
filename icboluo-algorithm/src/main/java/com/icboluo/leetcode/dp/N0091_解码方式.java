package com.icboluo.leetcode.dp;

class N0091_解码方式 {
    public static void main(String[] args) {
        var cla = new N0091_解码方式();
        assert cla.numDecodings("12") == 2;
        assert cla.numDecodings("226") == 3;
        assert cla.numDecodings("06") == 0;
    }

    // 从递归->到dp 超时
    public int numDecodings(String s) {
        return s.length() == 0 ? 0 : numDecodings(0, s);
    }

    private int numDecodings(int idx, String s) {
        if (idx == s.length()) {
            return 1;
        }
        if (s.charAt(idx) == '0') {
            return 0;
        }
        int res = numDecodings(idx + 1, s);
        // 27之类代表26个字母
        if (idx < s.length() - 1 && (s.charAt(idx) == '1' || (s.charAt(idx) == '2' && s.charAt(idx + 1) < '7'))) {
            res += numDecodings(idx + 2, s);
        }
        return res;
    }

    public int numDecodings2(String s) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i + 1];
                if (i < s.length() - 1 && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }
}
