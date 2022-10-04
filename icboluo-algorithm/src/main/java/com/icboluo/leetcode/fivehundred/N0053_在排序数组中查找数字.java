package com.icboluo.leetcode.fivehundred;

import java.math.BigDecimal;

class N0053_在排序数组中查找数字 {
    public static void main(String[] args) {
        N0053_在排序数组中查找数字 cla = new N0053_在排序数组中查找数字();
        int[] arr = {5, 7, 7, 8, 8, 10};
        int idx = cla.findCount3(arr, 8);
        System.out.println("idx = " + idx);
    }

    /**
     * 二分查找 TODO 没看懂
     *
     * @param arr
     * @param target
     * @return
     */
    private int findCount3(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        // 先求右指针
        while (start <= end) {
            double ceil = Math.ceil((start + end) / 2);
            mid = (int) Math.ceil((start + end) / 2);
            BigDecimal se = new BigDecimal(start + end);
            if (arr[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int right = start - 1;

        start = 0;
        end = arr.length - 1;
        while (start <= end) {
            mid = (int) Math.ceil((start + end) / 2);
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int left = end + 1;
        return right - left + 1;
    }

    /**
     * 从两边同时找，找到数据出现的左右位置，可以减少遍历数字出现的次数
     *
     * @param arr
     * @param target
     * @return
     */
    private int findCount2(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right && (arr[left] != target || arr[right] != target)) {
            if (left == right && arr[left] != target) {
                return 0;
            }
            if (arr[left] != target) {
                left++;
                // 不加else会不会快点
            } else if (arr[right] != target) {
                right--;
            }
        }
        return right - left + 1;
    }

    /**
     * 挨着查找
     *
     * @param arr
     * @param target
     * @return
     */
    private int findCount1(int[] arr, int target) {
        int count = 0;
        for (int item : arr) {
            if (item == target) {
                count++;
            }
        }
        return count;
    }
}
