package com.icboluo.leetcode.fivehundred;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class N0015_3数之和 {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = threeNumSum(arr);
        System.out.println("res = " + res);
    }

    /**
     * 3层循环求和不考虑
     * <p>
     * 升序数组+双指针：带区间的三数之和
     *
     * @param arr
     * @return
     */
    private static List<List<Integer>> threeNumSum(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int temp = arr[i] + arr[left] + arr[right];
                if (temp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[left]);
                    list.add(arr[right]);
                    ans.add(list);
                    if (arr[left + 1] == arr[left]) {
                        left++;
                    } else if (arr[right - 1] == arr[right]) {
                        right--;
                    } else {
                        break;
                    }
                } else if (temp < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
}
