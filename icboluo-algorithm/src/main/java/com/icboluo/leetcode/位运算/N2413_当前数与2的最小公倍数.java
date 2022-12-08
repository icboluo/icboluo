package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2022-12-08 22:35
 */
class N2413_当前数与2的最小公倍数 {
    public int smallestEvenMultiple(int n) {
        // 如果是偶数 n a<<0
        // 如果是奇数 2n a<<1
        // n&1->1 或者0
        return n << (n & 1);
    }
}
