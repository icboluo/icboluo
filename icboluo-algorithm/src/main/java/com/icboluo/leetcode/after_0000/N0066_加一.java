package com.icboluo.leetcode.after_0000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-10-27 13:15
 */
class N0066_加一 {
    public static void main(String[] args) {
        N0066_加一 cla = new N0066_加一();
        int[] arr = {1, 8, 9};
        int[] res = cla.plusOne(arr);
        System.out.println(Arrays.toString(res));
    }

    public int[] plusOne(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 9) {
                arr[i]++;
                return arr;
            }
            arr[i] = 0;
        }
        int[] res = new int[arr.length + 1];
        res[0] = 1;
        return res;
    }
}
