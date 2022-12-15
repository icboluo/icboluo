package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2022-11-26 11:27
 */
class N0476_补数 {
    public int findComplement(int num) {
        // 这一块的目的是求2的n次幂，超时是因为死循环了，这个等号有问题，成Integer的maxVal了
//        int n = 1;
//        // 10000-1=01111
//        while (n <= num) {
//            n = n << 1;
//        }
//        return n - 1 - num;
        int n = 0;
        while (n < num) {
            // 11111
            n = (n << 1) | 1;
        }
        return n - num;
    }
}
