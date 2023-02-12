package com.icboluo.leetcode.math;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-01-13 19:31
 */

class N0152_0238_0628_正数负数乘积运算 {
    /**
     * 最大乘积子数组，因为存在负数，所以我们不能简简单单的求最大值、最小值也要考虑
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // swap
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            // 其实也不用这么精细，我们可以直接计算出a，b判断哪一个是min max即可
            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i], nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * 0238 数组自身的乘积 low
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        return nums;
    }

    /**
     * 0628 数组中三个数的最大乘积
     * 3个数乘积期望是正的，所以最大的值不管怎样都得参与，其余的2个数可以选择前2个和后2个直接测试即可
     * 3个数乘积并不一定是正的，最大值可能为负数，不管怎么样，最大值均要参与运算，前2个和后2个分别代表最大值和最小值，和最大值相乘即可得到最大值
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int a = nums[0] * nums[1];
        int b = nums[nums.length - 2] * nums[nums.length - 3];
//        return Math.max(a, b) * nums[nums.length - 1];
        return Math.max(a * nums[nums.length - 1], b * nums[nums.length - 1]);
    }
}
