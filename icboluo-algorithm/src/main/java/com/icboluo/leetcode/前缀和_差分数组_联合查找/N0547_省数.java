package com.icboluo.leetcode.前缀和_差分数组_联合查找;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-12-27 22:33
 */
class N0547_省数 {
    public int findCircleNum(int[][] isConnected) {
        // 此题的误导性，介绍的是图的问题，习惯性作图计算；
        // 重新分析：求连通岛屿的数量，此题属于FloodFill算法，visited、for、dfs处理即可
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    graph.computeIfAbsent(i, key -> new LinkedList<>()).add(j);
                    graph.computeIfAbsent(j, key -> new LinkedList<>()).add(i);
                }
            }
        }

        int[] visited = new int[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (visited[i] == 0) {
                dfs(isConnected, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, int i, int[] visited) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(isConnected, j, visited);
            }
        }
    }

    public int findCircleNum2(int[][] isConnected) {
        UnionFind uf = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }
}
