package com.icboluo.datastructure.search;

/**
 * 二分查找不均衡，每次都取中间值，可以考虑数值的偏移量取取索引的偏移量
 *
 * @author icboluo
 * @since 2020/6/13 15:55
 */
class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int i = m1(arr, 0, arr.length - 1, 84);
        System.out.println("i = " + i);

    }

    private static int m1(int[] arr, int left, int right, int value) {
        //后面的条件不仅能够提供优化，而且能防止mid越界
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (value > midVal) {
            return m1(arr, mid + 1, left, value);
        } else if (value < midVal) {
            return m1(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
