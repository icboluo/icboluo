package com.icboluo.leetcode.二分查找;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-04-21 0:28
 */
class N2529_正整数和负整数的最大计数 {
    public int maximumCount1(int[] nums) {
        Map<Boolean, Long> greaterThan = Arrays.stream(nums)
                .filter(ele -> ele != 0)
                .boxed()
                .collect(Collectors.partitioningBy(ele -> ele > 0, Collectors.counting()));
        return (int) greaterThan.values().stream().mapToLong(Long::longValue).max().orElse(0);
    }

    /**
     * 因为有序，建议二分 FIXME ERROR TODO 1351
     *
     * @param nums
     * @return
     */
    public int maximumCount2(int[] nums) {
        int a = search(nums, 0);
        int b = nums.length - search(nums, 1);
        return Math.max(a, b);
    }

    /**
     * 不包括target，寻找右边界
     *
     * @param nums
     * @param target
     * @return
     */
    private int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
//                [mid+1,right]
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }
        return right;
    }
}
