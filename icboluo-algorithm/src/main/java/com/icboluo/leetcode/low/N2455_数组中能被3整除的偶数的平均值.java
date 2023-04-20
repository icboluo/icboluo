package com.icboluo.leetcode.low;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-04-20 23:38
 */
class N2455_数组中能被3整除的偶数的平均值 {
    public static void main(String[] args) {
        var cla = new N2455_数组中能被3整除的偶数的平均值();
        System.out.println(cla.averageValue(new int[]{94, 65, 82, 40, 79, 74, 92, 84, 37, 19, 16, 85, 20, 79, 25, 89, 55, 67, 84, 3, 79, 38, 16, 44, 2, 54, 58, 94, 69, 71, 14, 24, 13, 21}));
    }

    /**
     * @param nums
     * @return
     */
    public int averageValue(int[] nums) {
        double val = Arrays.stream(nums).filter(item -> item % 6 == 0).average().orElse(0);
        return (int) val;
    }
}
