package com.icboluo.datastructure.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author icboluo
 * @date 2020-08-07 12:39
 */
class VisitedVertex {
    public int[] already_arr;
    public int[] pre_visited;
    public int[] dis;

    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis, Integer.MAX_VALUE);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 判断顶点是否被访问过
     *
     * @param index
     * @return 被访问过返回true
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新顶点的前驱为index的节点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续访问并返回新的顶点
     *
     * @return
     */
    public int updateArr() {
        int min = Integer.MAX_VALUE, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show() {
        System.out.println("----------------------------");
        System.out.println(Arrays.toString(already_arr));
        System.out.println(Arrays.toString(pre_visited));
        System.out.println(Arrays.toString(dis));
        char[] vertex = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int count = 0;

        for (int i : dis) {
            if (i != Integer.MAX_VALUE) {
                System.out.println(vertex[count] + "(" + i + ")");
            } else {
                System.out.println("N ");
            }
            count++;
        }
    }
}
