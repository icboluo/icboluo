package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2023-05-22 20:59
 */
class N0033_搜索旋转排序数组 {
    // 二分查找 FIXME ERROR
    // 二分查找，不要嫌弃麻烦，需要找到真正的left和right，这样比较简单
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 先求最小值和最大值的索引
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 中间较大，说明最小值在右边，这里需要简单证明下
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        right += nums.length;
        // 使用%构建二分查找
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 中间较大，说明最小值在右边，这里需要简单证明下
            if (nums[mid % nums.length] > target) {
                right = mid - 1;
            } else if (nums[mid % nums.length] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
