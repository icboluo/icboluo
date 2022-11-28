package com.icboluo.leetcode.after_0400;

/**
 * @author icboluo
 * @since 2022-11-26 11:15
 */
class N0485_查找最大连续数 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            if (num == 1) {
                sum += 1;
                max = Math.max(max, sum);
            }else{
                sum = 0;
            }
        }
        return max;
    }
}
