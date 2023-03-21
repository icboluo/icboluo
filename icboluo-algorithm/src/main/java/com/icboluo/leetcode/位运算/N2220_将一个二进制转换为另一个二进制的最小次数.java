package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2023-03-21 22:29
 */
class N2220_将一个二进制转换为另一个二进制的最小次数 {
    /**
     * 统计位不一致的个数即可（异或
     *
     * @param start
     * @param goal
     * @return
     */
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        int count = 0;
        while (xor > 0) {
            count++;
            xor = xor & (xor - 1);
        }
        return count;
    }
}
