package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-30 21:56
 */
class N2239_最接近0的数字 {

    /**
     * 找到最接近0的数字，如果有多个相同，求里面的最大值
     * 也可以使用比较器
     *
     * @param nums
     * @return
     */
    public int findClosestNumber(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            // 正负数,注意逻辑分开写
            if (Math.abs(num) < Math.abs(res)) {
                res = num;
            } else if (num == Math.abs(res)) {
                res = num;
            }
        }
        return res;
    }
}
