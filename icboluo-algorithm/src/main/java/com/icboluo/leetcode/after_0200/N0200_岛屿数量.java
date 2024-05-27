package com.icboluo.leetcode.after_0200;

/**
 * @author icboluo
 * @since 2020-09-29 19:44
 */
class N0200_岛屿数量 {
    public static void main(String[] args) {
        var cla = new N0200_岛屿数量();

    }

    boolean[][] visited;
    int row;
    int col;

    /**
     * N0200 岛屿数量
     *
     * @param grid 岛屿二维数组
     * @return 孤立岛屿数量
     */
    public int numIslands(char[][] grid) {
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 边界
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return;
        }
        // 是否是海水
        if (grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
