package com.icboluo.leetcode.子串子序列;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-02-05 13:26
 */
public class N1588_所有奇数长度的子数组和 {
    /**
     * 三层暴力解 low
     *
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j >= 0; j -= 2) {
                sum += Arrays.stream(arr, j, i + 1).sum();
            }
        }
        return sum;
    }
}
