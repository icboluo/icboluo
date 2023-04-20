package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-20 23:41
 */
class N2460_对数组应用操作 {
    public int[] applyOperations(int[] nums) {
        // 按规则修改数组
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }
        // 将有用的数据提前
        int i = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[i++] = num;
            }
        }
        // 将没用的数据提前
        while (i < nums.length) {
            nums[i++] = 0;
        }
        return nums;
    }
}
