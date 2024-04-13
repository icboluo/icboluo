package com.icboluo.leetcode.计数排序;

/**
 * @author icboluo
 * @since 2024-02-26 22:27
 */
class N1608_特殊数组的特殊值 {
    // 非比较排序，将序列中的元素作为键存储在额外的数组空间中，将该元素的个数作为值存储在数组空间中，通过遍历该数组排序
    // 适用场景：最大值最小值差值不能过大，防止空间浪费；元素为整数 hard
    public int specialArray(int[] nums) {
        int[] count = new int[1001];
        for (int num : nums) {
            count[num]++;
        }
        // 这里的代码写的好难理解，这个题也好奇怪
        int len = nums.length;
        for (int i = 0; i <= 100; i++) {
            if (len == i) {
                return i;
            }
            len -= count[i];
        }
        return -1;
    }
}
