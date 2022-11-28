package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-25 11:45
 */
class N0868_二进制1的最大距离 {
    // TODO ERROR
    public int binaryGap(int n) {
        String s = Integer.toBinaryString(n);
        int first = s.indexOf('1');
        int last = s.lastIndexOf('1');
        return last - first;
    }
}
