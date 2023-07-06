package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2023-07-07 4:34
 */
class N0041_缺失的第一个正数 {
    // 把每个数字放到合适的位置，第一个不适合的位置就是,这串代码是需要一些证明的
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 当找到5的时候，将5放到arr[4]中
            while (nums[i] - 1 >= 0 && nums[i] - 1 < nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
