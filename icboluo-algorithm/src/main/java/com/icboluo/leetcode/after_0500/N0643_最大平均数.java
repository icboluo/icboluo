package com.icboluo.leetcode.after_0500;

/**
 * @author icboluo
 * @since 2022-09-20 0:35
 */
class N0643_最大平均数 {
    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int temp = 0;
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (right - left == k) {
                max = Math.max(max, temp);
                temp -= nums[left];
                left++;
            }
            temp += nums[right];
            right++;
        }
        max = Math.max(max, temp);
        return Double.parseDouble(max + "") / k;
    }

    public static void main(String[] args) {
        N0643_最大平均数 cla = new N0643_最大平均数();
        double maxAverage = cla.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4);
        System.out.println("maxAverage = " + maxAverage);
    }
}
