package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2022-11-25 17:03
 */
class N0136_只出现一次的数字 {
    // 异或自己为0
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
