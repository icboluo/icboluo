package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2022-12-08 22:49
 */
class N1952_一个数是否拥有3个除数 {
    public boolean isThree(int n) {
        // 一个数已经有2个除数 1和他本身，如果还要有一个，那么这个数为素数的平方
        if (n < 4) {
            return false;
        }
        // 必须可以开根号
        int sqrt = (int) Math.sqrt(n);
        if (sqrt * sqrt < n) {
            return false;
        }
        // 对半开，根号必须是素数
        for (int i = 2; i <= Math.sqrt(sqrt); i++) {
            if (sqrt % i == 0) {
                return false;
            }
        }
        return true;
    }
}
