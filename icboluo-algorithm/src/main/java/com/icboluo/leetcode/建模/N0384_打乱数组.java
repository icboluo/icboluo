package com.icboluo.leetcode.建模;

import java.util.Random;

/**
 * @author icboluo
 * @since 2023-06-05 23:25
 */
class N0384_打乱数组 {
    private int[] nums;

    private Random random;

    // 概率问题
    public N0384_打乱数组(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        if (nums == null) {
            return null;
        }
        int[] clone = nums.clone();
        for (int i = 0; i < clone.length; i++) {
            // 只要当前元素和剩下的元素进行交换即可，当前元素出现的顺序就乱了，证明略
            int r = (int) (Math.random() * (nums.length - i)) + i;
            int temp = clone[i];
            clone[i] = clone[r];
            clone[r] = temp;
        }
        return clone;
    }
}
