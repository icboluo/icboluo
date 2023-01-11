package com.icboluo.leetcode.after_0000;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author icboluo
 * @since 2020-10-09 21:44
 */
class N0034_搜索插入位置 {
    public static void main(String[] args) {
        N0034_搜索插入位置 cla = new N0034_搜索插入位置();
        int[] arr1 = {1, 3, 5, 6};
        var res = cla.searchRange1(arr1, 5);
        System.out.println("res = " + Arrays.toString(res));

        int[] arr2 = {5, 7, 7, 8, 8, 10};
        int idx = cla.findCount3(arr2, 8);
        System.out.println("idx = " + idx);
    }

    /**
     * 循环
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange1(int[] nums, int target) {
        int[] res = new int[2];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (res[0] == -1) {
                    res[0] = i;
                    res[1] = i;
                } else {
                    res[1] = i;
                }
            }
        }
        return res;
    }

    /**
     * 二分查找 TODO
     * ==mid
     * <mid+1 =left
     * >mid+1 =mid
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        return new int[2];
    }


    /**
     * OFF 0053 在排序数组中查找数字出现的次数
     * 挨着查找
     *
     * @param arr
     * @param target
     * @return
     */
    public int findCount1(int[] arr, int target) {
        int count = 0;
        for (int item : arr) {
            if (item == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * 从两边同时找，找到数据出现的左右位置，可以减少遍历数字出现的次数
     *
     * @param arr
     * @param target
     * @return
     */
    public int findCount2(int[] arr, int target) {
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
     * 二分查找 TODO 没看懂
     *
     * @param arr
     * @param target
     * @return
     */
    public int findCount3(int[] arr, int target) {
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
}
