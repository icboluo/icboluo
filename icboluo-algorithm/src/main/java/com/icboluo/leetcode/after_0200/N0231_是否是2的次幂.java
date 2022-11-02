package com.icboluo.leetcode.after_0200;

/**
 * @author icboluo
 * @since 2022-11-02 13:01
 */
class N0231_是否是2的次幂 {
    public static void main(String[] args) {
        N0231_是否是2的次幂 cla = new N0231_是否是2的次幂();
        boolean powerOfTwo = cla.isPowerOfTwo1(3);
        System.out.println("powerOfTwo = " + powerOfTwo);
    }

    public boolean isPowerOfTwo1(int n) {
        while (n >= 2) {
            if (n % 2 != 0) {
                return false;
            }
            n = n / 2;
        }
        return n == 1;
    }

    // TODO N==1 IS ERROR
    public boolean isPowerOfTwo2(int n) {
        // 10000 & 01111==00000
        // 也可以这样写 Integer.bitCount(n)==1
        return (n & (n - 1)) == 0;
    }
}
