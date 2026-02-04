package com.icboluo.leetcode.dp;

import com.icboluo.util.ArrayHelper;

import java.util.Arrays;

/**
 * 背包问题
 *
 * @author icboluo
 * @since 2020-08-05 12:26
 */
class a_背包问题_0_1背包 {
    public static void main(String[] args) {
        //物品重量
        int[] w = {1, 4, 3};
        //物品价值
        int[] val = {1500, 3000, 2000};
        //背包容量
        int m = 4;
        //物品的个数
        int n = val.length;

        int[][] path = new int[n + 1][m + 1];
        int[][] v = initArr(n + 1, m + 1);

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        ArrayHelper.print(v);
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }

    /**
     * 初始化一个为default的二维数组
     *
     * @param n 行数
     * @param m 列数
     * @return 初始化后的二维数组
     */
    public static int[][] initArr(int n, int m) {
        int[][] v = new int[n][m];
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 1;
        }
        Arrays.fill(v[0], 0);
        return v;
    }
}

