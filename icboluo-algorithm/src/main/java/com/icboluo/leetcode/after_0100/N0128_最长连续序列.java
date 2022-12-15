package com.icboluo.leetcode.after_0100;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2020-10-09 21:57
 */
class N0128_最长连续序列 {
    public static void main(String[] args) {
        N0128_最长连续序列 cla = new N0128_最长连续序列();
        int[] arr = {0};
        int res = cla.longestConsecutive(arr);
        System.out.println("res = " + res);
    }

    /**
     * 排序，遍历一遍即可 TODO ERROR
     *
     * @param arr
     * @return
     */
    public int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 题意要求去重
        arr = Arrays.stream(arr).boxed().distinct().sorted().mapToInt(Integer::intValue).toArray();
        int max = 1;
        int pre = arr[0];
        int temp = 1;
        for (int i = 1; i < arr.length; i++) {
            if (pre + 1 == arr[i]) {
                temp++;
            } else {
                temp = 1;
            }
            max = Math.max(max, temp);
            pre = arr[i];
        }
        return max;
    }

    /**
     * TODO 并查集
     *
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        return -1;
    }
}
