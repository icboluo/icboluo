package com.icboluo.leetcode.fivehundred;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2020-09-17 22:32
 */
class N0001_两数之和 {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] res = twoSum(arr, 9);
        System.out.println(Arrays.toString(res));
    }

    private static int[] twoSum(int[] arr, int n) {
        int[] res = new int[2];
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == n) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }
}
