package com.icboluo.leetcode.bfs;

import java.util.*;

/**
 * @author icboluo
 * @since 2023-03-29 19:34
 */
class N1631_最小体力消耗路径 {
    /**
     * djstl算法的前提是：一条路径越长（每增加一条边，路径的总长会越长；如果边长为负数，就会违反这个定律，不适用
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int g = heights.length;
        int k = heights[0].length;
        int[][] distArr = new int[g][k];
        for (int i = 0; i < g; i++) {
            Arrays.fill(distArr[i], Integer.MAX_VALUE);
        }
        distArr[0][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.effortFromStart));
        pq.offer(new Node(0, 0, 0));
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (poll.x == k - 1 && poll.y == g - 1) {
                return poll.effortFromStart;
            }
            if (poll.effortFromStart > distArr[poll.y][poll.x]) {
                continue;
            }
            for (int[] neighbor : neighbors(heights, poll.x, poll.y)) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                // 后面的是高度绝对差
                int effort = Math.max(distArr[poll.y][poll.x], Math.abs(heights[nextY][nextX] - heights[poll.y][poll.x]));
                if (distArr[nextY][nextX] > effort) {
                    distArr[nextY][nextX] = effort;
                    pq.offer(new Node(nextX, nextY, effort));
                }
            }
        }
        return -1;
    }

    private List<int[]> neighbors(int[][] matrix, int x, int y) {
        int g = matrix.length;
        int k = matrix[0].length;
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : directionArr) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            // 越界
            if (nextX < 0 || nextY < 0 || nextX >= k || nextY >= g) {
                continue;
            }
            neighbors.add(new int[]{nextX, nextY});
        }
        return neighbors;
    }

    private int[][] directionArr = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};


    class Node {
        int x;
        int y;
        /**
         * 体力
         */
        int effortFromStart;

        public Node(int x, int y, int effortFromStart) {
            this.x = x;
            this.y = y;
            this.effortFromStart = effortFromStart;
        }
    }
}
