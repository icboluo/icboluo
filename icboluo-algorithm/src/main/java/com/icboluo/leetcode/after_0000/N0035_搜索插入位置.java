package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-10-27 13:12
 */
class N0035_搜索插入位置 {
    public static void main(String[] args) {
        N0035_搜索插入位置 cla = new N0035_搜索插入位置();
        // 有序
        int[] arr = {1, 3, 5, 6};
        int idx = cla.searchInsert1(arr, 5);
        System.out.println("idx = " + idx);
    }

    /**
     * for 循环解决
     *
     * @param arr
     * @param n
     * @return
     */
    public int searchInsert1(int[] arr, int n) {
        int i = 0;
        for (; i < arr.length; i++) {
            if (arr[i] >= n) {
                break;
            }
        }
        return i;
    }

    /**
     * 二分查找 TODO ERROR
     *
     * @param arr
     * @param n
     * @return
     */
    public int searchInsert2(int[] arr, int n) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == n) {
                return mid;
            } else if (arr[mid] < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
