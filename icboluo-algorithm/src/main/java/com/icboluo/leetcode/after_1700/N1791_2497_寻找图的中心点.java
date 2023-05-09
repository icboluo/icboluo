package com.icboluo.leetcode.after_1700;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author icboluo
 * @since 2023-03-02 21:03
 */
class N1791_2497_寻找图的中心点 {
    // 图的中心点，我们只需要判断2条边即可
    public int findCenter(int[][] edges) {
        return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }

    // 2497 图的中心点连接组多的边
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        // 这块的k是递增的，对于非规律型图，需要使用map
        LinkedList<Integer>[] graph = new LinkedList[vals.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        // 感觉这里写的不对
        LinkedList<Integer> neighbor = new LinkedList<>();
        int max = 0;
        int idx = -1;
        for (int i = 0; i < graph.length; i++) {
            // 好像也有等于的情况
            if (graph[i].size() > max) {
                max = graph[i].size();
                neighbor = graph[i];
                idx = i;
            }
        }
        if (idx == -1) {
            return Arrays.stream(vals).max().orElse(0);
        }
        return neighbor.stream().mapToInt(i -> vals[i]).limit(k).filter(a -> a > 0).sum() + vals[idx];
    }
}
