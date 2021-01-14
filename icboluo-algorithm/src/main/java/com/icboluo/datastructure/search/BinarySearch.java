package com.icboluo.datastructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找（条件，有序
 *
 * @author icboluo
 * @date 2020/6/13 14:41
 */
class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        int resIndex = m1(arr, 0, arr.length - 1, 9);
        List<Integer> integers = m2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex = " + resIndex);
        System.out.println("integers = " + integers);
    }

    private static int m1(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {
            return m1(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return m1(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    private static List<Integer> m2(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {
            return m2(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return m2(arr, left, mid - 1, findValue);
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
