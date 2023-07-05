package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-06-30 18:53
 */
public class N0334_增加三元组子序列 {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int num : nums) {
            // 将发现的第一个元素赋值给small，迭代过程中，如果发现有更小的元素则替换
            if (num <= small) {
                small = num;
                // 将大于small的元素赋值给mid
            } else if (num <= mid) {
                mid = num;
            }else {
                // 如果当前元素大于small和mid，认为当前元素是big
                return true;
            }
        }
        return false;
    }
}
