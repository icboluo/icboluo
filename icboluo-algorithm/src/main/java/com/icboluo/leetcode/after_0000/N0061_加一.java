package com.icboluo.leetcode.after_0000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-10-27 13:15
 */
class N0061_加一 {
    public static void main(String[] args) {
        N0061_加一 cla = new N0061_加一();
        int[] arr = {1, 8, 9};
        int[] res = cla.jiayi(arr);
        System.out.println(Arrays.toString(res));
    }

    private int[] jiayi(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 9) {
                arr[i]++;
                return arr;
            }
            arr[i] = 0;
        }
        int[] res = new int[arr.length + 1];
        arr[0] = 1;
        return res;
    }
}
