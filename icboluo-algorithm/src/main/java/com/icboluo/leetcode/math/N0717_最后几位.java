package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-12-26 8:30
 */
class N0717_最后几位 {
    // bits最后一位是0，我们需要判断是10还是单纯的0
    // 1的个数是偶数可以消除 math low
    public boolean isOneBitCharacter(int[] bits) {
        int oneCount = 0;
        for (int i = bits.length - 2; i >= 0 && bits[i] == 1; i--) {
            oneCount++;
        }
        // 偶数个1：110  奇数个1：10
        return oneCount % 2 == 0;
    }
}
