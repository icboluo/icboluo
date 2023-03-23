package com.icboluo.leetcode.after_1700;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-23 23:40
 */
class N1925_直角三角形的个数 {
    /**
     * @param n 最长边为n
     * @return
     */
    public int countTriples(int n) {
        // 平方数组，当该数是平方的时候记为1
        int[] arr = new int[n * n + 1];
        IntStream.range(0, n + 1).forEach(i -> arr[i * i] = 1);
        int count = 0;
        for (int i = 1; i * i < n * n; i++) {
            for (int j = 1; i * i + j * j <= n * n; j++) {
                count += arr[i * i + j * j];
            }
        }
        return count;
    }

    /**
     * 数组中不相等的三元数组，长度为3的子序列各不相同的个数 it`s hard TODO
     *
     * @param nums
     * @return
     */
    public int unequalTriplets(int[] nums) {
        return -1;
    }
}
