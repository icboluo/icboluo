package com.icboluo.leetcode.二维数组;

/**
 * @author icboluo
 * @since 2023-02-28 22:10
 */
class N1812_判断二维数组的一个格子颜色 {
    public boolean squareIsWhite(String coordinates) {
        char ch = coordinates.charAt(0);
        int a = Character.digit(coordinates.charAt(1), 10);
        return ((ch - 'a') + a) % 2 == 0;
    }
}
