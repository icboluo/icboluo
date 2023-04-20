package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-04-20 23:49
 */
public class N2481_分割圆的最小切割数 {
    public int numberOfCuts(int n) {
        // 如果n==1,不需要切割
        if (n == 1) {
            return 0;
        }
        // 如果n为奇数，只能切半圆
        if (n % 2 == 1) {
            return n;
        } else {
            return n / 2;
        }
    }
}
