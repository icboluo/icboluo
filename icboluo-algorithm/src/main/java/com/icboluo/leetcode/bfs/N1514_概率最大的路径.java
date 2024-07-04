package com.icboluo.leetcode.bfs;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author icboluo
 * @since 2023-03-29 18:56
 */
class N1514_概率最大的路径 {
    public static void main(String[] args) {
        var cla = new N1514_概率最大的路径();
//        int[][] edges = {{0, 1}, {1, 2}, {0, 2},};
//        double[] succProb = {0.5, 0.5, 0.2};
//        double v = cla.maxProbability(3, edges, succProb, 0, 2);
//        System.out.println("v = " + v);

        int[][] edges = {{2, 0}, {2, 3}, {2, 5}, {2, 4}, {5, 3}, {3, 1}, {0, 3}, {4, 5}, {5, 0}};
        double[] succProb = {0.8701, 0.9375, 0.5994, 0.1174, 0.6767, 0.6912, 0.0488, 0.1562, 0.9872};
        System.out.println(cla.maxProbability(6, edges, succProb, 5, 3));
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
        Map<Integer, Node> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idxMap.put(i, new Node(i));
        }
        HashMap<Integer, List<Node>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edga = edges[i];
            int a = edga[0];
            int b = edga[1];
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(idxMap.get(b));
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(idxMap.get(a));
            idxMap.get(a).probMap.put(b, succProb[i]);
            idxMap.get(b).probMap.put(a, succProb[i]);
        }

        idxMap.get(start).probFromStart = 1;
        return dijikstra(start, end, idxMap, graph);
    }

    private double dijikstra(int start, int end, Map<Integer, Node> idxMap, Map<Integer, List<Node>> graph) {
        // 关于非最短路，当每条路径长度不等的时候，我们不能简单的层级遍历，层级遍历需要的是每一个元素仅访问一遍，下面的方法元素访问次数不受限制，但是也要防止死循环
        // 方法1：dij算法，利用优先级队列来处理弹出的元素优先级，通常伴随着内部类
        // 方法2：更新全图，求到达所有顶点的最优值即可，判断到达顶点是否最优来确认是否要天界此neighbor到queue
        // 这个比较器有问题，double->int会精度丢失  PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (int) (b.probFromStart - a.probFromStart));
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> BigDecimal.valueOf(b.probFromStart).compareTo(BigDecimal.valueOf(a.probFromStart)));
        pq.offer(idxMap.get(start));
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (poll.id == end) {
                return poll.probFromStart;
            }
            if (!graph.containsKey(poll.id)) {
                continue;
            }
            for (Node neighbor : graph.get(poll.id)) {
                Double edgeProb = poll.probMap.get(neighbor.id);
                double prob = poll.probFromStart * edgeProb;
                if (prob > neighbor.probFromStart) {
                    // optimize 严禁优先级队列中存在2个完全相同的元素，这样会使比较器出现奇怪的问题
                    pq.remove(neighbor);
                    // optimize 严禁修改优先级队列中的value值,这样原本的结构不会发生改变，修改值必须放到offer之前
                    neighbor.probFromStart = prob;
                    pq.offer(neighbor);
                    System.out.println(prob);
                }
            }
        }
        return 0.0;
    }

    private double bellmanFord(int start, int end, Map<Integer, Node> idxMap, Map<Integer, List<Node>> graph) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(idxMap.get(start));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (!graph.containsKey(poll.id)) {
                continue;
            }
            for (Node neighbor : graph.get(poll.id)) {
                Double edgeProb = poll.probMap.get(neighbor.id);
                double prob = poll.probFromStart * edgeProb;
                if (prob > neighbor.probFromStart) {
                    neighbor.probFromStart = prob;
                    queue.offer(neighbor);
                }
            }
        }
        return idxMap.get(end).probFromStart;
    }


    static class Node {
        int id;
        /**
         * 概率
         */
        double probFromStart;

        Map<Integer, Double> probMap = new HashMap<>();

        public Node(int id) {
            this.id = id;
        }

        public Node(int id, double probFromStart) {
            this.id = id;
            this.probFromStart = probFromStart;
        }
    }
}
