package com.icboluo.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author icboluo
 * @date 2020-08-07 12:32
 */
class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @param index 出发顶点的下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 0; i < vertex.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    /**
     * 更新index顶点到周围顶点的距离和前驱节点
     *
     * @param index
     */
    private void update(int index) {
        //出发顶点到index顶点的距离，从index顶点到j顶点的距离的和
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            //如果i顶点没有被访问过，并且len小于出发顶点到i顶点的距离，就需要更新
            if (vv.in(index) && len < vv.getDis(i)) {
                vv.updatePre(i, index);
                vv.updateDis(i, len);
            }
        }
    }
    public void  showdjs() {
        vv.show();
    }
}
