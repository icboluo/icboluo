package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-04-20 23:57
 */
public class N2485_找到枢轴整数 {
    /**
     * 切成2半，前半部分和等于后半部分和 FIXME ERROR
     *
     * @param n
     * @return
     */
    public int pivotInteger1(int n) {
        int sum = n * (n - 1);
        int target = (int) Math.sqrt(sum);
        return target * target == sum ? target : -1;
    }

    public int pivotInteger2(int n) {
        int sum = n * (n - 1);
        // 可以分left+right=sum，判断是否存在一个点，使left=right
        int left = 0;
        int right = sum;
        for (int i = 0; i < n; i++) {
            if (left == right) {
                return i;
            }
            left += i;
            right -= i;
        }
        return -1;
    }
}
