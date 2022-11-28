package com.icboluo.leetcode.after_0200;

import com.icboluo.common.Node;

import java.util.LinkedList;

/**
 * @author icboluo
 * @since 2022-11-26 14:30
 */
class N0207_课程表 {
    public static void main(String[] args) {
        N0207_课程表 cla = new N0207_课程表();
        int num = 2;
        int[][] arr = {{1, 0}};
        boolean b = cla.canFinish(num, arr);
        System.out.println("b = " + b);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            prerequisites[i][0]++;
            prerequisites[i][1]++;
        }
        return canFinish2(numCourses, prerequisites);
    }

    /**
     * 全局是否被访问
     */
    boolean[] visited;
    /**
     * 当前路径是否被访问
     */
    boolean[] isPath;

    /**
     * 是否有环
     */
    boolean hasCycle;

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        isPath = new boolean[numCourses];

        LinkedList<Integer>[] graph = new LinkedList[numCourses];
        // 禁止这样写，这样写是浅拷贝
//        Arrays.fill(graph, new LinkedList<>());
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] item : prerequisites) {
            // 这里是由1到0
            graph[item[1]].add(item[0]);
        }

        // 这里采取的是随机遍历
        for (int i = 0; i < numCourses; i++) {
            list(graph, i);
        }
        // 无环代表可以被全部访问
        return !hasCycle;
    }

    private void list(LinkedList<Integer>[] graph, int start) {
        if (isPath[start]) {
            hasCycle = true;
            return;
        }
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        isPath[start] = true;
        for (Integer child : graph[start]) {
            list(graph, child);
        }
        isPath[start] = false;
    }

    /**
     * 原来的拓扑排序类似于 BFS 版本
     * 这里的处理是认为给的元素是从1开始的
     *
     * @param num
     * @param arr
     * @return
     */
    private boolean canFinish2(int num, int[][] arr) {
        // 2种做法在前面的写法是一样的，但是思路差距很大；
        // 我们操作的是索引，需要在操作的时候把idx和val区分开来；idx=val-1
        LinkedList<Integer>[] graph = new LinkedList[num];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        // par: 1, sub: 2 3 我们可以认为，做2之前需要做1，做3之前需要做1
        for (int[] item : arr) {
            graph[item[1] - 1].add(item[0] - 1);
        }
        // 入度，上层；按我的理解他是出度
        int[] indegree = new int[num];
        for (int[] item : arr) {
            indegree[item[0] - 1]++;
        }
        // 找出入度为0的元素
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        int[] ans = new int[num];
        // BFS 所有元素以及下一层相邻元素，这里采取的是顺序遍历
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            ans[count++] = poll;
            for (Integer sub : graph[poll]) {
                indegree[sub]--;
                if (indegree[sub] == 0) {
                    queue.add(sub);
                }
            }
        }
        return count == num;
    }

    // DFS 算法：关注点在节点
    private void traverse1(Node root) {
        if (root == null) {
            return;
        }
        System.out.println("进入节点" + root);
        for (Node child : root.children) {
            traverse1(child);
        }
        System.out.println("离开节点" + root);
    }

    // 回溯算法，关注点在树枝
    private void traverse2(Node root) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            System.out.printf("从 %s 到 %s" + root, child);
            traverse2(child);
            System.out.printf("从 %s 到 %s" + child, root);
        }
    }
}
