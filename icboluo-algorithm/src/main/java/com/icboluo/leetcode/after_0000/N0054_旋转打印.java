package com.icboluo.leetcode.after_0000;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-09-20 0:19
 */
class N0054_旋转打印 {

    public static void main(String[] args) {
        N0054_旋转打印 cla = new N0054_旋转打印();
        int[][] arr = new int[][]{
                {1, 2, 3, 4},//.......
                {5, 6, 7, 8},//........
                {9, 10, 11, 12}
        };
        List<Integer> integers = cla.spiralOrder(arr);
        System.out.println("integers = " + integers);

        int[][] ints = cla.genMatrix(3);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int u = 0;
        int d = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;
        LinkedList<Integer> ans = new LinkedList<>();
        while (ans.size() < matrix.length * matrix[0].length) {
            if (u <= d) {
                for (int i = l; i <= r; i++) {
                    ans.add(matrix[u][i]);
                }
                u++;
            }
            if (l <= r) {
                for (int i = u; i <= d; i++) {
                    ans.add(matrix[i][r]);
                }
                r--;
            }
            if (u <= d) {
                for (int i = r; i >= l; i--) {
                    ans.add(matrix[d][i]);
                }
                d--;
            }
            if (l <= r) {
                for (int i = d; i >= u; i--) {
                    ans.add(matrix[i][l]);
                }
                l++;
            }
        }
        return ans;
    }

    // TODO what`s this
    public int[][] genMatrix(int n) {
        int[][] arr = new int[n][n];
        int u = 0, l = 0;
        int d = n - 1, r = n - 1;
        int a = 1;
        // 为什么这里需要加等号，上面不需要加：因为上面的是从0开始计数，这里是从1开始计数
        while (a <= n * n) {
            if (u <= d) {
                for (int i = l; i <= r; i++) {
                    arr[u][i] = a++;
                }
                u++;
            }
            if (l <= r) {
                for (int i = u; i <= d; i++) {
                    arr[i][r] = a++;
                }
                r--;
            }
            if (u <= d) {
                for (int i = r; i >= l; i--) {
                    arr[d][i] = a++;
                }
                d--;
            }
            if (l <= r) {
                for (int i = d; i >= u; i--) {
                    arr[i][l] = a++;
                }
                l++;
            }
        }
        return arr;
    }
}
