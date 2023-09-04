package com.icboluo.leetcode.after_0000;

import com.icboluo.util.ArrayHelper;

/**
 * @author icboluo
 * @since 2023-09-04 22:19
 */
class N0031_下一个排列 {
    /**
     * 1 2 3 d 下一个排列是1 3 2，尽可能地变更后面的值
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        // 首先，寻找第一个正序增长的位置
        int k;
        for (k = nums.length - 2; k >= 0; k--) {
            if (nums[k] < nums[k + 1]) {
                break;
            }
        }
        // 整个数组都是逆序的
        if (k == -1) {
            ArrayHelper.reverse(nums);
        } else {
            // TODO it`s hard to understand
        }
    }
}
