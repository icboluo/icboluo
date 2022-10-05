package com.icboluo.leetcode.fivehundred;

import java.util.Arrays;

class N0075_颜色分类三路快排 {
    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
        N0075_颜色分类三路快排 cla = new N0075_颜色分类三路快排();
        cla.paixu1(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 三路快排
     *
     * @param arr
     */
    private void paixu1(int[] arr) {
        int zero = -1;
        int end = arr.length;
        int temp;
        for (int i = 0; i < end; ) {
            // 如果是1，不管继续往前走
            if (arr[i] == 1) {
                i++;
                // 如果是2，直接交换到最后面；因为指针不会发生移动，所以，会再次检查这个节点
            } else if (arr[i] == 2) {
                end--;
                temp = arr[i];
                arr[i] = arr[end];
                arr[end] = temp;
                // 如果是0，交换到最前面，并且指针向后移动一位
            } else {
                zero++;
                temp = arr[zero];
                arr[zero] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
    }

    private void paixu2(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        // TODO 这个写法是错误的，很奇怪的写法，不能修正
        for (int i = 0; i < right; i++) {
            if (arr[i] == 0) {
                int temp = arr[left];
                arr[left] = arr[i];
                arr[i] = temp;
                left++;
            } else if (arr[i] == 2) {
                int temp = arr[right];
                arr[right] = arr[i];
                arr[i] = temp;
                right--;
            }
        }
    }
}
