package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-04-20 23:57
 */
public class N2485_找到枢轴整数 {
    /**
     * 切成2半，前半部分和等于后半部分和
     *
     * @param n
     * @return
     */
    public int pivotInteger1(int n) {
        // 前n项和
        int sum = n * (n + 1) / 2;
        int target = (int) Math.sqrt(sum);
        return target * target == sum ? target : -1;
    }

    public int pivotInteger2(int n) {
        int sum = n * (n + 1) / 2;
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 比较小
            if (mid * mid - sum < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left * left - sum == 0 ? left : -1;
    }

    public int pivotInteger3(int n) {
        int sum = n * (n + 1) / 2;
        // 可以分left+right=sum，判断是否存在一个点，使left=right
        int left = 0;
        int right = sum;
        for (int i = 0; i <= n; i++) {
            if (left == right - i) {
                return i;
            }
            left += i;
            right -= i;
        }
        return -1;
    }
}
