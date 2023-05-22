package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2023-05-22 21:14
 */
class N0153_从旋转排序数组中查找最小值 {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 如果上一个比较大，说明递减，此处刚好是结果
            if (mid != 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            // 如果中间比较大，2边比较小，说明最小区间在右边
            if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[Math.max(0, left - 1)];
    }
}
