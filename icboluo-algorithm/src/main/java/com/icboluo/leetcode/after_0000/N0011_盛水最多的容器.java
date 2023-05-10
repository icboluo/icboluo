package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2020-09-28 21:06
 */
class N0011_盛水最多的容器 {
    public static void main(String[] args) {
        var cla = new N0011_盛水最多的容器();
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int i = cla.maxArea1(arr);
        System.out.println("i = " + i);
    }


    /**
     * 挨着比对，双层for 求出最大面积
     * 超时
     */
    public int maxArea1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int temp = Math.min(arr[i], arr[j]) * (j - i);
                max = Math.max(max, temp);
            }
        }
        return max;
    }

    /**
     * 双指针，用较短的高度的指针向内移动
     */
    public int maxArea2(int[] arr) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] < arr[right]) {
                // 左边比较小，用左边的墙
                max = Math.max(max, arr[left] * (right - left));
                left++;
            } else {
                max = Math.max(max, arr[right] * (right - left));
                right--;
            }
        }
        return max;
    }
}
