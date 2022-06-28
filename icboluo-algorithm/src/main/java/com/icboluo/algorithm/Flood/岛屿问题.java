package com.icboluo.algorithm.Flood;

import java.util.HashSet;
import java.util.Set;

/**
 * 本质上是图的遍历问题，也就是多叉树遍历，也是深度优先搜索（Depth First Search，简称 DFS）
 * 和回溯算法有一定相似的地方
 *
 * @author icboluo
 * @since 2022-06-28 20:51
 */
public class 岛屿问题 {
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
        // 也可以不定义访问数组，直接修改原数组为0表示无法再访问，后序不再说明此写法
        visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfs1(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs1(char[][] grid, int i, int j) {
        // 边界
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return;
        }
        // 是否是海水
        if (grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs1(grid, i - 1, j);
        dfs1(grid, i + 1, j);
        dfs1(grid, i, j - 1);
        dfs1(grid, i, j + 1);
    }

    /**
     * N1254封闭岛屿数量：此题与下面一个题一样
     *
     * @param grid 岛屿二维数组
     * @return 封闭岛屿数量
     */
    public int closedIsland(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        // 先求靠边的岛屿，然后标记为已访问
        for (int i = 0; i < grid.length; i++) {
            dfs2(grid, i, 0);
            dfs2(grid, i, col - 1);
        }
        for (int j = 0; j < col; j++) {
            dfs2(grid, 0, j);
            dfs2(grid, row - 1, j);
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    dfs2(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs2(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return;
        }
        if (grid[i][j] == 1 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs2(grid, i - 1, j);
        dfs2(grid, i + 1, j);
        dfs2(grid, i, j - 1);
        dfs2(grid, i, j + 1);
    }

    int temp;

    /**
     * N1020飞地数量：利用返回值处理
     *
     * @param grid 岛屿二维数组
     * @return 飞地数量
     */
    public int numEnclaves(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    temp = 0;
                    boolean stepAside = dfs3(grid, i, j);
                    if (!stepAside) {
                        ans += temp;
                    }
                }
            }
        }
        return ans;
    }

    private boolean dfs3(int[][] grid, int i, int j) {
        // 如果越界，说明可以到达上一个边界，说明靠边
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return true;
        }
        // 当前是水，肯定不靠边
        if (grid[i][j] == 0 || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        temp++;
        boolean a = dfs3(grid, i - 1, j);
        boolean b = dfs3(grid, i + 1, j);
        boolean c = dfs3(grid, i, j - 1);
        boolean d = dfs3(grid, i, j + 1);
        return a || b || c || d;
    }

    /**
     * N0695 岛屿的最大面积
     *
     * @param grid 岛屿二维数组
     * @return 最大面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs4(grid, i, j);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }

    private int dfs4(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return 0;
        }
        if (grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        int a = dfs4(grid, i - 1, j);
        int b = dfs4(grid, i + 1, j);
        int c = dfs4(grid, i, j - 1);
        int d = dfs4(grid, i, j + 1);
        return 1 + a + b + c + d;
    }

    /**
     * N1905 计数子岛
     *
     * @param grid1 父岛屿
     * @param grid2 子岛屿
     * @return 子岛数量
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        row = grid1.length;
        col = grid1[0].length;
        visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 子岛屿是岛，父岛屿是水，不合理，舍去子岛屿的所有
                // 此块舍去之后，相当于岛屿2全部为子岛屿
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfs5(grid2, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid2[i][j] == 1 && !visited[i][j]) {
                    dfs5(grid2, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs5(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return;
        }
        if (grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs5(grid, i - 1, j);
        dfs5(grid, i + 1, j);
        dfs5(grid, i, j - 1);
        dfs5(grid, i, j + 1);
    }

    /**
     * N0694 不同的岛屿数量
     *
     * @param grid 岛屿二维数组
     * @return 不同的岛屿数量
     */
    int numDistinctIslands(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                StringBuilder sb = new StringBuilder();
                dfs6(grid, i, j, sb, 0);
                set.add(sb.toString());
            }
        }
        return set.size();
    }

    private void dfs6(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return;
        }
        if (grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        // 多叉树的前序遍历
        // 此块也类似于回溯算法
        sb.append(dir).append(',');
        dfs6(grid, i - 1, j, sb, 1);
        dfs6(grid, i + 1, j, sb, 2);
        dfs6(grid, i, j - 1, sb, 3);
        dfs6(grid, i, j + 1, sb, 4);
        // 多叉树的后序遍历
        // 撤销操作是有必要记录的，下右撤销和下撤销右撤销，结果是完全不同的
        sb.append(-dir).append(',');
    }
}
