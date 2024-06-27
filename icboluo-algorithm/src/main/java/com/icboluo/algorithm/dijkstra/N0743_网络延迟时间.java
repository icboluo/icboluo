package com.icboluo.algorithm.dijkstra;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2022-11-26 15:50
 */
class N0743_网络延迟时间 {
    public static void main(String[] args) {
        var cla = new N0743_网络延迟时间();
        int[][] arr = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int i = cla.networkDelayTime(arr, 4, 2);
        System.out.println("i = " + i);
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从1开始，我们就暂存0啥都不计了
        LinkedList<int[]>[] graph = new LinkedList[n + 1];
        // 注意等号
        for (int i = 0; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : times) {
            int start = edge[0];
            int end = edge[1];
            graph[start].add(new int[]{end, edge[2]});
        }
        int[] shortestPath = shortestPath(k, graph);
        int max = 0;
        // 注意1开始
        for (int i = 1; i < shortestPath.length; i++) {
            int item = shortestPath[i];
            if (item == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, item);
        }
        return max;
    }

    private int[] shortestPath(int start, LinkedList<int[]>[] graph) {
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.id - b.id);
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int id = poll.id;
            int dist = poll.distFromStart;
            if (dist > distTo[id]) {
                continue;
            }
            for (int[] neighbor : graph[id]) {
                int nextId = neighbor[0];
                int nextDist = dist + neighbor[1];
                if (distTo[nextId] > nextDist) {
                    distTo[nextId] = nextDist;
                    pq.offer(new Node(nextId, nextDist));
                }
            }
        }
        return distTo;
    }

    class Node {
        int id;
        /**
         * distance 距离
         */
        int distFromStart;

        public Node(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }
}
