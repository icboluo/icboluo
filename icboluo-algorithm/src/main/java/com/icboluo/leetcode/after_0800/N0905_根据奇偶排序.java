package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-22 22:30
 */
class N0905_根据奇偶排序 {
    // TODO ERROR
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right++] % 2 == 0) {
                nums[left++] = nums[right];
            }
        }
        return nums;
    }


}
