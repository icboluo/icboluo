package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-21 1:03
 */
public class N2574_左右元素和的差值 {
    // 需要注意断点
    public int[] leftRightDifference(int[] nums) {
        int[] res = new int[nums.length];
        // left sum
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] + nums[i - 1];
        }
        // - right sum
        int next = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            int temp = nums[i + 1] + next;
            next = temp;
            res[i] = Math.abs(res[i] - temp);
        }
        return res;
    }
}
