package com.icboluo.leetcode.两数之和;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author icboluo
 * @since 2022-08-29 21:55
 */
public class Demo01_TwoSum {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] res = twoSum1(arr, 9);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 暴力穷举
     *
     * @param arr
     * @param target
     * @return
     */
    private static int[] twoSum1(int[] arr, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    /**
     * Hash优化
     *
     * @param arr
     * @param target
     * @return
     */
    private static int[] twoSum2(int[] arr, int target) {
        int[] ans = new int[2];
        HashMap<Integer, Integer> eleIdxMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            eleIdxMap.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int need = target - arr[i];
            if (eleIdxMap.containsKey(need) && eleIdxMap.get(need) != i) {
                ans[0] = i;
                ans[1] = eleIdxMap.get(need);
                return ans;
            }
        }
        return ans;
    }

    /**
     * 双指针
     * 快慢指针：常用作处理链表中是否有环的问题
     * 左右指针：二分查找等算法
     *
     * @param arr
     * @param target
     * @return
     */
    private static int[] twoSum3(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }
}
