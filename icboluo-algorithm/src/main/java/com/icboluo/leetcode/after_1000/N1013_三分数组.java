package com.icboluo.leetcode.after_1000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-12-08 22:03
 */
class N1013_三分数组 {
    /**
     * 将数组分为3份，保证每份的和相等 TODO ERROR
     *
     * @param arr
     * @return
     */
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        int left = 0;
        int right = arr.length - 1;
        int target = sum / 3;
        if (sum % 3 != 0) {
            return false;
        }
        int leftSum = 0;
        int rightSum = 0;
        // 注意这个-1，保证中间有数据
        while (left < right - 1) {
            if (leftSum != target) {
                leftSum += arr[left++];
            } else if (rightSum != target) {
                rightSum += arr[right--];
            } else {
                break;
            }
        }
        return leftSum == target && rightSum == target;
    }
}
