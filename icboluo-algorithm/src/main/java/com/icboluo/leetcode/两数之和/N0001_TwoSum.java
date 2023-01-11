package com.icboluo.leetcode.两数之和;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 求两数之和等于target
 * 1.可以使用双层for循环遍历两数和比较
 * 2.将数组元素存储到哈希表中，一次遍历取另一个元素查看是否可以取到
 * 设计求2数之和的数据结构
 * 1.add函数将数组存储到哈希表中，find函数单次遍历
 * 2.add函数将数组和2数之和分别存储到数组中，find函数直接到sum数组中找元素，时间复杂度O（n）
 *
 * @author icboluo
 * @since 2021-30-26 21:30
 */
class N0001_TwoSum {
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
