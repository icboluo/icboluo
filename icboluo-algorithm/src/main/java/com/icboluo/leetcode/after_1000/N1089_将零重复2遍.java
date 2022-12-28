package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2022-12-28 19:58
 */
class N1089_将零重复2遍 {
    public void duplicateZeros(int[] arr) {
        int zero = 0;
        for (int num : arr) {
            if (num == 0) {
                zero++;
            }
        }

        int i = arr.length - 1;
        int j = arr.length + zero - 1;
        // 从后向前，因为后面的数都是丢弃的
        while (i < j) {
            if (arr[i] != 0) {
                if (j < arr.length) {
                    arr[j] = arr[i];
                }
            } else {
                if (j < arr.length) {
                    arr[j] = arr[i];
                }
                // j减2次是为了重复0的情况
                j--;
                if (j < arr.length) {
                    arr[j] = arr[i];
                }
            }
            i--;
            j--;
        }
    }
}
