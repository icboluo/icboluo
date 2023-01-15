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

    /**
     * 1004 最大连续数3；最大可以交换k次，求连续的1最多有多少个
     * k是交换次数，那么一个子串有1个0就需要交换1次；答案中有更简单的解法
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0;
        int winZero = 0;
        int max = 0;
        while (r < nums.length) {
            if (nums[r++] == 0) {
                winZero++;
            }
            while (winZero > k) {
                if (nums[l++] == 0) {
                    winZero--;
                }
            }
            max = Math.max(max, r - l);
        }
        return max;
    }
}
