package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2023-01-07 23:11
 */
class N1374_生成具有奇数个字符的字符串 {
    /**
     * 奇怪的题目，如果n为奇数，则返回aaaaa....,如果为偶数，则返回baaaaa...
     *
     * @param n
     * @return
     */
    public String generateTheString(int n) {
        // 奇数 n+1%2==0
        return "a".repeat(n - (n + 1) % 2) + "b".repeat((n + 1) % 2);
    }
}
