package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2023-01-07 16:17
 */
class N1486_数组异或运算 {
    /**
     * 用题目给的公式直接来
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= start + 2 * i;
        }
        return res;
    }
}
