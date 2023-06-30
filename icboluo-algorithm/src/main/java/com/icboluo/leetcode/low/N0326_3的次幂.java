package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-06-30 18:42
 */
public class N0326_3的次幂 {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
