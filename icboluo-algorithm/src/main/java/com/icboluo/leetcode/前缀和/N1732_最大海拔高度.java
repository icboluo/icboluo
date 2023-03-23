package com.icboluo.leetcode.前缀和;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-24 0:22
 */
class N1732_最大海拔高度 {
    public int largestAltitude(int[] gain) {
        int[] preSum = new int[gain.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < gain.length; i++) {
            preSum[i + 1] = preSum[i] + gain[i];
        }
        return Arrays.stream(preSum).max().getAsInt();
    }
}
