package com.icboluo.leetcode.二维数组;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2023-02-05 13:15
 */
public class N0542_BFS_01矩阵 {
    /**
     * 当前单元格的距离 0的最近距离
     *
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        // 标准的BFS,使用队列维护起始位置
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    // 让mat中非0元素记录到0的距离，默认值为最大值
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            bfs(mat, poll, queue, 1, 0);
            bfs(mat, poll, queue, -1, 0);
            bfs(mat, poll, queue, 0, 1);
            bfs(mat, poll, queue, 0, -1);
        }
        return mat;
    }

    private void bfs(int[][] mat, int[] arr, Queue<int[]> queue, int dx, int dy) {
        int x = arr[0] + dx;
        int y = arr[1] + dy;
        if (x < 0 || x >= mat.length || y < 0 || y >= mat[0].length || mat[x][y] < mat[arr[0]][arr[1]] + 1) {
            return;
        }
        queue.add(new int[]{x, y});
        // 其实，首次给x，y赋值的时候，就已经是最短距离了，所以可以判断当前单元格如果不是默认值，跳出即可
        mat[x][y] = mat[arr[0]][arr[1]] + 1;
    }
}
