package com.icboluo.leetcode.前缀和_差分数组_联合查找;

/**
 * @author icboluo
 * @since 2022-08-02 23:13
 */
class N0303_区域和检索 {
    private final int[] preNum;

    public N0303_区域和检索(int[] nums) {
        preNum = new int[nums.length + 1];
        for (int i = 1; i < preNum.length; i++) {
            preNum[i] = nums[i - 1] + preNum[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return preNum[right + 1] - preNum[left];
    }

    public static void main(String[] args) {
        int[] arr = {-2, 0, 3, -5, 2, -1};
        var cla = new N0303_区域和检索(arr);
        int i = cla.sumRange(0, 2);
        System.out.println("i = " + i);
    }
}
