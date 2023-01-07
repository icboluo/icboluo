package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2022-11-25 11:45
 */
class N0868_二进制1的最大距离 {
    /**
     * 任意2个相邻的最大距离，不是2个1的最大距离
     *
     * @param n
     * @return
     */
    public int binaryGap1(int n) {
        String s = Integer.toBinaryString(n);
        int first = s.indexOf('1');
        int last = s.lastIndexOf('1');
        return last - first;
    }

    /**
     * 我们尝试将n向右移动一次 FIXME ERROR
     *
     * @param n
     * @return
     */
    public int binaryGap2(int n) {
        int max = 0;
        // 我们要尽可能的使第一个1无效，所以，temp的初始值要尽可能的小;temp相当于一个计数器
        int temp = Integer.MIN_VALUE;
        while (n > 0) {
            n = n >> 1;
            // 如果最后一位是1，说明找到边界了
            if (n % 2 == 1) {
                max = Math.max(max, temp);
                temp = 1;
            } else {
                temp++;
            }
        }
        return max;
    }
}
