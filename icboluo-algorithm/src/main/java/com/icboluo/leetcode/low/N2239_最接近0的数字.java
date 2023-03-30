package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-30 21:56
 */
public class N2239_最接近0的数字 {
    // 也可以使用比较器 FIXME ERROR
    public int findClosestNumber(int[] nums) {
        int res = Integer.MAX_VALUE;
        int val = Integer.MAX_VALUE;
        for (int num : nums) {
            if (Math.abs(num) <= res) {
                if (num >= 0 || num != val) {
                    val = num;
                }
                res = Math.abs(num);
            }
        }
        return res;
    }
}
