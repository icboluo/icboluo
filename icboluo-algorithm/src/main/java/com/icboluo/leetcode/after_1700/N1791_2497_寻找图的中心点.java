package com.icboluo.leetcode.after_1700;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2023-03-02 21:03
 */
class N1791_2497_寻找图的中心点 {
    public static void main(String[] args) {
        var cla = new N1791_2497_寻找图的中心点();
        System.out.println(cla.maxStarSum(new int[]{1, 2, 3, 4, 10, -10, -20}, new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}, {3, 5}, {3, 6}}, 1)); // 16
        System.out.println(cla.maxStarSum(new int[]{-5}, new int[][]{}, 0)); // -5
        System.out.println(cla.maxStarSum(new int[]{-1, 0}, new int[][]{}, 1));// 0
        System.out.println(cla.maxStarSum(new int[]{1, -8, 0}, new int[][]{{1, 0}, {2, 1}}, 2)); // 1
        System.out.println(cla.maxStarSum(new int[]{-2, -1, -2}, new int[][]{{0, 2}}, 1)); // -1
    }

    // 1791 图的中心点，我们只需要判断2条边即可
    public int findCenter(int[][] edges) {
        return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }

    // 2497 图的中心点连接组多的边，只计算直接连接着的就可以了，不用计算连接着的连接着的 FIXME ERROR
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        if (k == 0) {
            return Arrays.stream(vals).max().orElse(0);
        }
        // 这块的k是递增的，对于非规律型图，需要使用map
        LinkedList<Integer>[] graph = new LinkedList[vals.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int res = Arrays.stream(vals).max().orElse(0);
        for (int i = 0; i < graph.length; i++) {
            // 每个节点求一次和，算最大值即可
            res = Math.max(res, topK(graph[i], vals[i], k, vals));
        }
        return res;
    }

    private int topK(LinkedList<Integer> neighbors, int val, int k, int[] vals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Integer neighbor : neighbors) {
            pq.offer(vals[neighbor]);
        }
        if (!pq.isEmpty()) {
            // 也可以在建图的时候直接不要这个边
            return pq.stream().mapToInt(Integer::intValue).filter(a -> a > 0).limit(k).sum() + val;
        }
        return val;
    }
}
