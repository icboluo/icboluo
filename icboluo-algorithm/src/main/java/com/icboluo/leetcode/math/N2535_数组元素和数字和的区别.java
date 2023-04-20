package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-04-21 0:40
 */
class N2535_数组元素和数字和的区别 {
    public static void main(String[] args) {
        var cla = new N2535_数组元素和数字和的区别();
        System.out.println(cla.differenceOfSum(new int[]{1, 15, 6, 3}));
    }

    /**
     * 数组sum和 每个 每位数和的差值
     *
     * @param nums
     * @return
     */
    public int differenceOfSum(int[] nums) {
        // 一个变量计算结果，避免2个变量一个递增而溢出
        int res = 0;
        // 1次for循环，避免一个stream+，一个for减
        for (int num : nums) {
            res += num;
            while (num > 0) {
                res -= num % 10;
                num = num / 10;
            }
        }
        return res;
    }
}
