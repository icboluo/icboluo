package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2022-12-28 22:05
 */
class N1232_坐标能否构成一条直线 {
    public boolean checkStraightLine(int[][] coordinates) {
        int x = coordinates[0][0];
        int y = coordinates[0][1];

        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];
        int yc = y1 - y;
        int xc = x1 - x;
        for (int i = 2; i < coordinates.length; i++) {
            int tempX = coordinates[i][0];
            int tempY = coordinates[i][1];
            if ((yc * (tempX - x)) != (xc * (tempY - y))) {
                return false;
            }
        }
        return true;
    }
}
