package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2022-12-30 1:02
 */
class N2078_2个不同房子之间的最大距离 {
    /**
     * 直觉告诉我们，最大距离就是第一个和最后一个不同房子之间的距离
     * 当然了，定义头尾的值，迭代遍历，更新最优解也是可以的
     *
     * @param colors
     * @return
     */
    public int maxDistance(int[] colors) {
        int i = 0;
        int j = colors.length - 1;
        while (colors[0] == colors[j]) {
            j--;
        }
        while (colors[i] == colors[colors.length - 1]) {
            i++;
        }
        return Math.max(j, colors.length - 1 - i);
    }
}
