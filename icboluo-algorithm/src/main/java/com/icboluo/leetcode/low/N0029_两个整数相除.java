package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-06-12 1:28
 */
class N0029_两个整数相除 {
    public int divide(int dividend, int divisor) {
        long l = (long) dividend / (long) divisor;
        if (l > Math.pow(2, 31) - 1) {
            return (int) Math.pow(2, 31);
        }
        if (l < Math.pow(-2, 31)) {
            return (int) Math.pow(-2, 31);
        }
        return (int) l;
    }
}
