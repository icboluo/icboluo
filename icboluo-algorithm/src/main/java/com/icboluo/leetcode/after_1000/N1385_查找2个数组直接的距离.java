package com.icboluo.leetcode.after_1000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-01-07 22:49
 */
class N1385_查找2个数组直接的距离 {
    /**
     * 2个数组之间距离小于d的个数
     * 需要全量比较
     *
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValue1(int[] arr1, int[] arr2, int d) {
        int count = 0;
        for (int num1 : arr1) {
            boolean isValid = true;
            for (int num2 : arr2) {
                if (Math.abs(num1 - num2) <= d) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                count++;
            }
        }
        return count;
    }

    /**
     * 对第二个数组排序，取最大值和最小值 TODO
     *
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValue2(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;
        return count;
    }
}
