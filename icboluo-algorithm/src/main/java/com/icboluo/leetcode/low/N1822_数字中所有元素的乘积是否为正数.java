package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-02-28 21:35
 */
class N1822_数字中所有元素的乘积是否为正数 {
    // 数组中所有元素的乘积是否为正数 low 包
    public int arraySign(int[] nums) {
        int xiaoYu0 = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                xiaoYu0++;
            }
        }
        return xiaoYu0 % 2 == 0 ? 1 : -1;
    }
}
