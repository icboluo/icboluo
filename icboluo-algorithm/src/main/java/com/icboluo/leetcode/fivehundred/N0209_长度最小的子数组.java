package com.icboluo.leetcode.fivehundred;

/**
 * @author icboluo
 * @since 2022-10-27 13:18
 */
class N0209_长度最小的子数组 {
    public static void main(String[] args) {
        N0209_长度最小的子数组 cla = new N0209_长度最小的子数组();
        int[] arr = {2, 3, 1, 2, 4, 3};
        int i = cla.minSubArr(arr, 7);
        System.out.println("i = " + i);
    }


    /**
     * 双指针
     *
     * @param arr
     * @param n
     * @return
     */
    private int minSubArr(int[] arr, int n) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (left < arr.length) {
            if (right == arr.length) {
                sum -= arr[left];
                left++;
                if (sum < n) {
                    break;
                }
            }
            if (sum < n) {
                sum += arr[right];
                right++;
            } else if (sum >= n) {
                sum -= arr[left];
                min = Math.min(min, right - left);
                left++;
            }
        }
        return min;
    }
}
