package com.icboluo.leetcode.after_0200;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-01-14 14:23
 */
class N0217_0219_2357_数组中有无出现多次的数据 {
    public boolean containsDuplicate(int[] nums) {
        return nums.length != Arrays.stream(nums).distinct().count();
    }

    /**
     * 0219 存在2个元素 arr[i]，arr[j]相等，且j-i<k
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 保证set中的数据量是固定的，也可以用set大小来控制
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            // 如果set中已经包含
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

        /**
         * 2357 通过减去等量使数组为0，数组每次减去一个数，这个数小于等于最小元素（0除外），返回数组全部为0需要的最少步骤数
         * 试着讨论：对于相同的元素，操作至0的步骤是相同的，所以可以使用set降低arr的长度；其实每一个元素都只有一个操作次数
         *
         * @param nums
         * @return
         */
        public int minimumOperations(int[] nums) {
            return (int) Arrays.stream(nums).distinct().filter(ele -> ele != 0).count();
        }
}
