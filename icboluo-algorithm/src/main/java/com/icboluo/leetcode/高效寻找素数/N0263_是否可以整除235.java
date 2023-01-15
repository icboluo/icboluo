package com.icboluo.leetcode.高效寻找素数;

/**
 * @author icboluo
 * @since 2023-01-14 18:42
 */
class N0263_是否可以整除235 {
    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
