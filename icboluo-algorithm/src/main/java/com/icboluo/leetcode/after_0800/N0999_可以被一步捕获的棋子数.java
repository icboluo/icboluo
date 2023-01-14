package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2023-01-08 15:32
 */
class N0999_可以被一步捕获的棋子数 {
    public int numRookCaptures(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 白车
                if (board[i][j] == 'R') {
                    // 只能到4个位置
                    return cap(board, i, j, 1, 0) + cap(board, i, j, 0, 1)
                            + cap(board, i, j, -1, 0) + cap(board, i, j, 0, -1);
                }
            }
        }
        return 0;
    }

    private int cap(char[][] board, int x, int y, int dx, int dy) {
        // 不是象
        while (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != 'B') {
            if (board[x][y] == 'p') {
                return 1;
            }
            x += dx;
            y += dy;
        }
        return 0;
    }

    /**
     * 2257 计算网格中未受保护的单元格
     *
     * @param m
     * @param n
     * @param guards
     * @param walls
     * @return
     */
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // 网格
        char[][] grid = new char[m][n];
        // 墙
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = 'w';
        }
        // 卫兵
        for (int[] guard : guards) {
            grid[guard[0]][guard[1]] = 'g';
        }
        int count = m * n - guards.length - walls.length;
        // 以卫兵做为开始
        for (int[] guard : guards) {
            // 向四个方向移动，判断最多移动的距离
            count -= dd(grid, guard[0], guard[1], 1, 0);
            count -= dd(grid, guard[0], guard[1], -1, 0);
            count -= dd(grid, guard[0], guard[1], 0, 1);
            count -= dd(grid, guard[0], guard[1], 0, -1);
        }
        return count;
    }

    private int dd(char[][] arr, int x, int y, int dx, int dy) {
        // 先移动一次
        x += dx;
        y += dy;
        int count = 0;
        // 这里取边界
        while (x >= 0 && x < arr.length && y >= 0 && y < arr[0].length && arr[x][y] != 'g' && arr[x][y] != 'w') {
            if (arr[x][y] != 'p') {
                count++;
                arr[x][y] = 'p';
            }
            // 再不断移动
            x += dx;
            y += dy;
        }
        return count;
    }
}
