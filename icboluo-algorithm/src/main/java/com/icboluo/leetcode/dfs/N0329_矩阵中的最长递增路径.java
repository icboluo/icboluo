package com.icboluo.leetcode.dfs;

/**
 * @author icboluo
 * @since 2023-06-10 12:55
 */
class N0329_矩阵中的最长递增路径 {
    int[][] cache;

    public int longestIncreasingPath(int[][] matrix) {
        int max = 1;
        cache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int len = dfs(matrix, i, j);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            // 从当前点扩散，找较大的元素，如果下一个的距离小于当前，就不要往下一个找了
            // 因为大小关系是单向的，所以没必要做一个visited，不会出现死循环
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(matrix, x, y);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}
