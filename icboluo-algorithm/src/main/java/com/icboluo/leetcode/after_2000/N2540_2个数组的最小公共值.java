package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-21 0:44
 */
public class N2540_2个数组的最小公共值 {
    /**
     * 递增数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            }
            if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return -1;
    }
}
