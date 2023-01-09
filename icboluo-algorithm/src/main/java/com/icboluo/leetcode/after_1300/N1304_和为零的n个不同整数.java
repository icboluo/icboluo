package com.icboluo.leetcode.after_1300;

/**
 * @author icboluo
 * @since 2022-12-28 19:56
 */
class N1304_和为零的n个不同整数 {
    // 随便搞一个相反数数组即可
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2 - n + 1;
        }
        return arr;
    }
}
