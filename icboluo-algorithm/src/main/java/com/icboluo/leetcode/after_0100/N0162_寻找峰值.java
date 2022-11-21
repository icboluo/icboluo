package com.icboluo.leetcode.after_0100;

/**
 * @author icboluo
 * @since 2022-10-10 21:41
 */
class N0162_寻找峰值 {
    public static void main(String[] args) {
        N0162_寻找峰值 cla = new N0162_寻找峰值();
        int[] arr = {1, 2, 3, 1};
        int i = cla.findPeakElement(arr);
        System.out.println("i = " + i);
    }

    /**
     * 线性扫描
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    /**
     * 二分查找
     *
     * @param nums
     * @return
     */
    public int findPeakElement2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
