package com.icboluo.leetcode.after_0800;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-12-29 0:00
 */
class N0908_0910_最小范围 {
    /**
     * 你可以对数组中的元素进行加 -k->k之间的任意数
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeI(int[] nums, int k) {
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length - 1];
        return Math.max(0, max - min - 2 * k);
    }

    /**
     * 0910 最小范围2 TODO 没有理解 FIXME ERROR
     * it`s hard to understand
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        int res = max - nums[0];
        // 做一个推论，+-n其实相当于 +2n||0 也就是说，元素可以选择+2n或者不变
/*        for (int i = 0; i < nums.length - 1; i++) {
            // 从最小的值开始，最大值可能为当前节点
            max = Math.max(max, nums[i] + 2 * k);
            // 可以获得i的最大值，最小值可能为i+1；也可能是加了之后依然最小，所以是i，又因为0比i肯定要小，所以是0
            int min = Math.min(nums[i + 1], nums[0] + 2 * k);
            res = Math.min(res, max - min);
        }*/
        for (int i = 0; i < nums.length - 1; i++) {
            int min;
            if (nums[i] + 2 * k > max) {
                max = nums[i] + 2 * k;
                min = Math.min(nums[i + 1], nums[0] + 2 * k);
            }else{
                min = nums[0] + 2 * k;
            }
            res = Math.min(res, max - min);
        }
        return res;
    }
}
