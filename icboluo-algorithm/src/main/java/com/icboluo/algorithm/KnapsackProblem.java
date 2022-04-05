package com.icboluo.algorithm;

import com.icboluo.util.ArrayHelper;

/**
 * 背包问题
 *
 * @author icboluo
 * @since 2020-08-05 12:26
 */
public class KnapsackProblem {
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
        int[][] v = ArrayHelper.initArr(n + 1, m + 1);

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }else {
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
                System.out.printf("第%d个商品放入背包\n" , i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}

