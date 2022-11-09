package com.icboluo.leetcode.after_0600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-07 23:31
 */
class N0724_1991中枢索引 {
    public static void main(String[] args) {
        N0724_1991中枢索引 cla = new N0724_1991中枢索引();
        int i = cla.pivotIndex(new int[]{1, 7, 3, 6, 5, 6});
        System.out.println("i = " + i);
    }

    // todo error 不应该假定的认为arr是有序的，用左右指针的做法是不合理的
    public int pivotIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int cha = 0;
        while (left < right) {
            if (cha > 0) {
                cha -= nums[right--];
            } else {
                cha += nums[left++];
            }
        }
        return cha == 0 ? left : -1;
    }

    // 1991 查找数组中的中间索引 ,没写完
    public int findMiddleIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        Map<Integer, Integer> totalIdxMap = new HashMap<>();
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 这块只是为了处理0的特殊值的，完全可以独立处理从1开始遍历
            if (i - 1 > 0) {
                leftSum += nums[i - 1];
            }
            totalIdxMap.putIfAbsent(leftSum * 2 + nums[i], 1);
        }
        return sum;
    }
}
