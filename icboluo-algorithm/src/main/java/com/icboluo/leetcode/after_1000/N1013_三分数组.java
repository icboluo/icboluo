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
        // 不取等要保证中间有值（不为空数组
        while (left < right) {
            if (leftSum != target || left == 0) {
                leftSum += arr[left++];
            } else if (rightSum != target || right == arr.length - 1) {
                rightSum += arr[right--];
            } else {
                break;
            }
        }
        return leftSum == target && rightSum == target;
    }
}
