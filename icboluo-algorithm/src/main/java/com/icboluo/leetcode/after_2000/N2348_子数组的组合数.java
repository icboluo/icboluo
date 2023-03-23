package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-01-15 20:06
 */
class N2348_子数组的组合数 {
    /**
     * 零填充子数组的数量 0的组数
     *
     * @param nums
     * @return
     */
    public long zeroFilledSubarray(int[] nums) {
        // 0 +1
        // 00 +2
        // 000 +3 不错的一个推论
        long res = 0;
        int cur = 0;
        for (int num : nums) {
            if (num == 0) {
                cur++;
                res += cur;
            } else {
                cur = 0;
            }
        }
        return res;
    }
}
