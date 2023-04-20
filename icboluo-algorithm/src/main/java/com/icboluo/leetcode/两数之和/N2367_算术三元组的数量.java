package com.icboluo.leetcode.两数之和;

import java.util.HashSet;

/**
 * @author icboluo
 * @since 2023-04-20 22:54
 */
class N2367_算术三元组的数量 {
    /**
     * abc c-b=b-a=diff
     *
     * @param nums
     * @param diff
     * @return
     */
    public int arithmeticTriplets(int[] nums, int diff) {
        HashSet<Integer> max = new HashSet<>();
        int count = 0;
        for (int item : nums) {
            if (max.contains(item - diff) && max.contains(item - 2 * diff)) {
                count++;
            }
            max.add(item);
        }
        return count;
    }

    /**
     * 2475 数组中不相等的三元数组
     * 3个数互不相等 TODO 看不懂代码
     *
     * @param nums
     * @return
     */
    public int unequalTriplets(int[] nums) {
        return -1;
    }
}
