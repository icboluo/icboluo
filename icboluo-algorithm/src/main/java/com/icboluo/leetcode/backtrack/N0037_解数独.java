package com.icboluo.leetcode.backtrack;

/**
 * @author icboluo
 * @since 2023-09-15 18:49
 */
class N0037_解数独 {
    // 回溯,如果给定的条件越少，代码执行的效率越高
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int i, int j) {
        // 如果是最后一列，则进入下一行第1列,此块写成 board[i].length 会越界
        if (j == 9) {
            return backtrack(board, i + 1, 0);
        }
        // 找到一个可行解，就返回
        if (i == board.length) {
            return true;
        }
        // 已经有数字，不处理
        if ('.' != board[i][j]) {
            return backtrack(board, i, j + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, i, j, ch)) {
                continue;
            }
            board[i][j] = ch;
            // 找到一个可行解 结束
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    private boolean isValid(char[][] board, int i, int j, char target) {
        for (int x = 0; x < 9; x++) {
            if (board[i][x] == target) {
                return false;
            }
            if (board[x][j] == target) {
                return false;
            }
            // 3*3方格是否合法 /3*3的结果是每个3*3方格的左上角位置，然后行/3，列%3即可
            if (board[(i / 3) * 3 + x / 3][(j / 3) * 3 + x % 3] == target) {
                return false;
            }
        }
        return true;
    }
}
