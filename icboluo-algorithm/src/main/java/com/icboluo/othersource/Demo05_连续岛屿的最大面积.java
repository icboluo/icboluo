package com.icboluo.othersource;

/**
 * @author icboluo
 * @date 2021-11-17 22:26
 */
public class Demo05_连续岛屿的最大面积 {

    private int length;

    private int[][] arr;

    private int maxArea(int[][] arr) {
        length = arr.length;
        this.arr = arr;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int curArea = area(i, j);
                ans = Math.max(ans, curArea);
            }
        }
        return ans;
    }

    private int area(int i, int j) {
        if (keDa(i + 1, j)) {
            return area(i + 1, j) + 1;
        }
        if (keDa(i - 1, j)) {
            return area(i - 1, j) + 1;
        }
        if (keDa(i, j + 1)) {
            return area(i, j + 1) + 1;
        }
        if (keDa(i, j - 1)) {
            return area(i, j - 1) + 1;
        }
        return 0;
    }

    private boolean keDa(int i, int j) {
        if (i < 0 || i > length - 1) {
            return false;
        }
        if (j < 0 || j > length - 1) {
            return false;
        }
        return arr[i][j] == 1;
    }
}
