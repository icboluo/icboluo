package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-02-28 21:29
 */
class N1800_最大升序子数组和 {
    // 这里的代码写的还是不错的
    public int maxAscendingSum(int[] nums) {
        int max = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            // 我们要保证每一个元素都被访问到，所以外层循环应该拥有所有的元素
            if (i < nums.length - 1 && nums[i] >= nums[i + 1]) {
                temp += nums[i];
                max = Math.max(max, temp);
                temp = 0;
            } else {
                temp += nums[i];
            }
            if (i == nums.length - 1) {
                max = Math.max(max, temp);
            }
        }
        return max;
    }
}
