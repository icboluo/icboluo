package com.icboluo.leetcode.math;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-01-07 16:07
 */
class N1502_连续2个元素的差是否相同 {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int cha = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (cha != arr[i] - arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
