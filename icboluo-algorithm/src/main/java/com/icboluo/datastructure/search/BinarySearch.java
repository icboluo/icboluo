package com.icboluo.datastructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找（条件，有序
 * todo 求左侧边界/右侧边界 用左闭右闭区间和左闭右开区间
 *
 * @author icboluo
 * @date 2020/6/13 14:41
 */
class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        int resIndex = findEle(arr, 0, arr.length - 1, 9);
        List<Integer> integers = findList(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex = " + resIndex);
        System.out.println("integers = " + integers);
    }

    private static int findEle(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {
            return findEle(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return findEle(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找非递归
     *
     * @param arr    待排序数组
     * @param target 目标元素
     * @return 目标元素索引
     */
    public static int binarySearchNoRecur(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static List<Integer> findList(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {
            return findList(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return findList(arr, left, mid - 1, findValue);
        } else {
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findValue) {
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findValue) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
