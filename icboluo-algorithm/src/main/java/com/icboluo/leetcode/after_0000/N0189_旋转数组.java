package com.icboluo.leetcode.after_0000;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-10-10 21:50
 */
class N0189_旋转数组 {
    public static void main(String[] args) {
        N0189_旋转数组 cla = new N0189_旋转数组();
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        cla.rotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    // TODO error
    public void rotate(int[] arr, int n) {
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, n - 1);
        reverse(arr, n, arr.length - 1);
    }

    private void reverse(int[] arr, int left, int right) {
        int temp;
        while (left < right) {
            temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }
}
