package com.icboluo.leetcode.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2023-03-29 18:56
 */
class N1514_概率最大的路径 {
    // FIXME ERROR
    public static void main(String[] args) {
        var cla = new N1514_概率最大的路径();
        int[][] edges = {{0, 1}, {1, 2}, {0, 2},};
        double[] succProb = {0.5, 0.5, 0.2};
        double v = cla.maxProbability(3, edges, succProb, 0, 2);
        System.out.println("v = " + v);
    }

    /**
     * @param n        n个节点，下标从0开始
     * @param edges    边
     * @param succProb 成功率
     * @param start
     * @param end
     * @return
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        LinkedList<Object[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int startTemp = edges[i][0];
            int endTemp = edges[i][1];
            graph[startTemp].add(new Object[]{endTemp, succProb[i]});
            graph[endTemp].add(new Object[]{startTemp, succProb[i]});
        }
        return shortPath(start, end, graph);
    }

    private double shortPath(int start, int end, LinkedList<Object[]>[] graph) {
        double[] distArr = new double[graph.length];
        Arrays.fill(distArr, 0);
        distArr[start] = 1;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (int) (b.probFromStart - a.probFromStart));
        pq.offer(new Node(start, 1));
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (poll.id == end) {
                return poll.probFromStart;
            }
            if (poll.probFromStart < distArr[poll.id]) {
                continue;
            }
            for (Object[] neighbor : graph[poll.id]) {
                int nextId = (int) neighbor[0];
                double nextProb = (double) neighbor[1];
                double prob = distArr[poll.id] * nextProb;
                if (distArr[nextId] < prob) {
                    distArr[nextId] = prob;
                    pq.offer(new Node(nextId, prob));
                }
            }
        }
        return 0.0;
    }


    class Node {
        int id;
        /**
         * 概率
         */
        double probFromStart;

        public Node(int id, double probFromStart) {
            this.id = id;
            this.probFromStart = probFromStart;
        }
    }
}
