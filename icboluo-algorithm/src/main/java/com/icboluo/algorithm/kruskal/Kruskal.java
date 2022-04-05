package com.icboluo.algorithm.kruskal;

/**
 * @author icboluo
 * @since 2020-08-05 22:41
 */
class Kruskal {
    private int edgeNum;
    /**
     * 顶点
     */
    private char[] vertexs;
    /**
     * 边
     */
    private int[][] matrix;
    private static final int inf = Integer.MAX_VALUE;

    public Kruskal(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                if (this.matrix[i][j] != inf) {
                    edgeNum++;
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * @param ch 顶点的值 比如 'a'
     * @return 返回顶点下标，找不到返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边放到数组中
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != inf) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点
     *
     * @param ends
     * @param i
     * @return
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 对边进行排序
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    public void kruskal() {
        //结果数组的索引
        int index = 0;
        //最小生成树的顶点
        int[] ends = new int[edgeNum];

        EData[] results = new EData[edgeNum];
        EData[] edges = getEdges();

        sortEdges(edges);

        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                ends[m] = n;
                results[index++] = edges[i];
            }
        }
        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }
    }
}
