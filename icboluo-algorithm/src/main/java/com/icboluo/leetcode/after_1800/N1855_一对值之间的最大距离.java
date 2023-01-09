package com.icboluo.leetcode.after_1800;

/**
 * @author icboluo
 * @since 2023-01-04 19:18
 */
class N1855_一对值之间的最大距离 {
    /**
     * num2>=num1;&& j>=i TODO 超时
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int dis = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i; j < nums2.length; j++) {
                if (nums1[i] <= nums2[j]) {
                    dis = Math.max(dis, j - i);
                }
            }
        }
        return dis;
    }
}
