package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-07-06 20:12
 */
public class N0167_两数之和 {
    /**
     * 只能使用左右指针，不可以使用快慢指针
     *
     * @param numbers 排序数组
     * @param target  目标值
     * @return 索引+1位置
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int temp = numbers[left] + numbers[right];
            if (temp > target) {
                right--;
            } else if (temp < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[2];
    }
}
