package com.icboluo.leetcode.math.最大公约数;

/**
 * @author icboluo
 * @since 2023-04-20 23:12
 */
class N2427_公约数的个数 {
    /**
     * 暴力解
     *
     * @param a
     * @param b
     * @return
     */
    public int commonFactors(int a, int b) {
        int res = 1;
        for (int i = 2; i <= gcd(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                res++;
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
