package com.icboluo.algorithm.dijkstra;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-10-25 12:15
 */
public class biaozhun {

    /**
     * 权重
     *
     * @param start
     * @param end
     * @return
     */
    private int weight(int start, int end) {
        return -1;
    }

    /**
     * 邻节点
     *
     * @param start
     * @return
     */
    private List<Integer> neighbor(int start) {
        return null;
    }

    /**
     * 最短距离
     *
     * @param start
     * @param graph
     * @return
     */
    private int[] shortPath(int start, List<Integer>[] graph) {
        // distances距离，start到i的最短距离
        int[] distArr = new int[graph.length];
        Arrays.fill(distArr, Integer.MAX_VALUE);
        distArr[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distFromStart));
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int id = poll.id;
            // 比当前要长，忽略
            if (poll.distFromStart > distArr[id]) {
                continue;
            }
            for (Integer neighborId : graph[id]) {
                int nextWeight = distArr[id] + weight(id, neighborId);
                if (distArr[neighborId] > nextWeight) {
                    distArr[neighborId] = nextWeight;
                    pq.offer(new Node(neighborId, nextWeight));
                }
            }
        }
        return distArr;
    }

    @AllArgsConstructor
    static class Node {
        int id;
        int distFromStart;
    }
}
