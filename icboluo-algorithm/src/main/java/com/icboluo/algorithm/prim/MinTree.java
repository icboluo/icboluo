package com.icboluo.algorithm.prim;

import java.util.Arrays;

/**
 * @author icboluo
 * @date 2020-08-05 17:22
 */
 class MinTree {
    public void creatGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 从图的第v个顶点开始得到最小生成树
     *
     * @param graph
     * @param v
     */
    public void prim(MGraph graph, int v) {
        //标记顶点是否被访问过
        int[] visited = new int[graph.verxs];
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = Integer.MAX_VALUE;
        for (int k = 1; k < graph.verxs; k++) {
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println(graph.data[h1] + "->" + graph.data[h2] + "权" + minWeight);
            visited[h2] = 1;
            minWeight = Integer.MAX_VALUE;
        }
    }
}
