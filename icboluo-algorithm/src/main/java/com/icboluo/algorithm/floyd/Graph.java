package com.icboluo.algorithm.floyd;

import com.icboluo.util.ArrayHelper;

import java.util.Arrays;

/**
 * @author icboluo
 * @date 2020-08-10 12:57
 */
class Graph {
    private char[] vertex;
    private int[][] dis;
    private int[][] pre;

    /**
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        ArrayHelper.print(dis);
        ArrayHelper.print(pre);
    }

    public void floyd() {
        int len = 0;
        //对中间顶点遍历
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
