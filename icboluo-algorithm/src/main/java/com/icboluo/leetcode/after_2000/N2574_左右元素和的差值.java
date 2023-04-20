package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-21 1:03
 */
public class N2574_左右元素和的差值 {
    // FIXME ERROR
    public int[] leftRigthDifference(int[] nums) {
        int[] res = new int[nums.length];
        // left sum
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] + nums[i];
        }
        // - right sum
        int next = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[i] + next;
            next = temp;
            res[i] = Math.abs(res[i] - temp);
        }
        return res;
    }
}
