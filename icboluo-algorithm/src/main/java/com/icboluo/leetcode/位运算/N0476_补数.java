package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2022-11-26 11:27
 */
class N0476_补数 {
    // TODO 超时
    public int findComplement(int num) {
        int n = 1;
        // 10000-1=01111
        while (n <= num) {
            n = n << 1;
        }
        return n - 1 - num;
    }
}
