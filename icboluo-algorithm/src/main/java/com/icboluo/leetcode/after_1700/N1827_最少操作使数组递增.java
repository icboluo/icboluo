package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-02-28 21:36
 */
class N1827_最少操作使数组递增 {
    // 可以给数组元素+1，使数组严格递增，返回最少操作数
    public int minOperations(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                count += nums[i] - nums[i + 1] + 1;
                nums[i + 1] = nums[i] + 1;
            }
        }
        return count;
    }
}
