package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2023-05-22 20:59
 */
class N0033_搜索旋转排序数组 {
    // 二分查找 FIXME ERROR
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 注意这里，整体代码细节诡异
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 3 4 5 6 1 2
            if (nums[mid] > nums[right]) {
                // 左边中间值，右边只能更大或者更小，在右方的2种场景
                if (target > nums[mid] || target <= nums[right]) {
                    left = mid + 1;
                } else {
                    // 为什么不能使用闭区间 ？？？
                    right = mid - 1;
                }
            } else if (nums[mid] < nums[right]) {
                // 5 6 1 2 3 4 右边是递增场景，左边只能更大或者更小
                if (target > nums[mid] && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        // left == right + 1 &&  这个好像没必要吧
        if (target != nums[left]) {
            return -1;
        }
        return left;
    }
}
