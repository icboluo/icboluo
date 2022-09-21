package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-07-06 21:40
 */
class N0042_接雨水 {
    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int minHeight = Math.min(leftMax[i], rightMax[i]);
            sum += minHeight - height[i];
        }
        return sum;
    }
}
