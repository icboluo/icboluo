package com.icboluo.leetcode.dfs;

/**
 * @author icboluo
 * @since 2023-05-10 18:33
 */
public class N0079_单词搜索 {
    boolean[][] isVisited;

    public boolean exist(char[][] board, String word) {
        isVisited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!isVisited[i][j] && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int step) {
        if (step == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (isVisited[i][j] || board[i][j] != word.charAt(step)) {
            return false;
        }
        isVisited[i][j] = true;
        boolean res = dfs(board, i - 1, j, word, step + 1)
                ||dfs(board, i + 1, j, word, step + 1)
                ||dfs(board, i, j-1, word, step + 1)
                ||dfs(board, i, j+1, word, step + 1);
        // 一定要回溯
        isVisited[i][j] = false;
        return res;
    }
}
