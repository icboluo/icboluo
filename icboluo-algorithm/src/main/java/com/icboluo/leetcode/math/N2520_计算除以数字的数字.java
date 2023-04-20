package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-04-21 0:25
 */
class N2520_计算除以数字的数字 {
    /**
     * 1248可以整除 1/2/4/8 所以个数是4
     *
     * @param num
     * @return
     */
    public int countDigits(int num) {
        int count = 0;
        int temp = num;
        while (temp > 0) {
            if (num % (temp % 10) == 0) {
                count++;
            }
            temp /= 10;
        }
        return count;
    }
}
