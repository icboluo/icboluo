package com.icboluo.leetcode.bfs;

import java.util.*;

/**
 * @author icboluo
 * @since 2023-03-29 22:35
 */
class N1971_查找图中是否存在路径 {
    public static void main(String[] args) {
        var cla = new N1971_查找图中是否存在路径();
        int[][] arr = {{0, 4}};
        System.out.println(cla.validPath1(5, arr, 0, 4));
    }

    /**
     * 方案1：BFS/拓扑排序
     *
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            Set<Integer> set1 = graph.getOrDefault(edge[0], new HashSet<>());
            set1.add(edge[1]);
            graph.put(edge[0], set1);

            Set<Integer> set0 = graph.getOrDefault(edge[1], new HashSet<>());
            set0.add(edge[0]);
            graph.put(edge[1], set0);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        Set<Integer> visited = new HashSet<>();
        visited.add(source);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                assert poll != null;
                if (poll == destination) {
                    return true;
                }
                if (graph.containsKey(poll)) {
                    for (Integer neighbor : graph.get(poll)) {
                        if (visited.add(neighbor)) {
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }
        return false;
    }

    // TODO 方案2 DFS
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        return false;
    }
    // TODO 方案3 连通分量，因为是无向图，我们仅仅需要起点和终点在一个连通分量重（而不需要拓扑排序获取路径长度，代码复杂
}
