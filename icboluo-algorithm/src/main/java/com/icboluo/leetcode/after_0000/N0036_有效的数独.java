package com.icboluo.leetcode.after_0000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2020-10-09 21:49
 */
class N0036_有效的数独 {
    public static void main(String[] args) {
        /*
        给每一个横、竖、九宫格建立map
        map if can get return false
        九宫格 i/3*3+j/3 i影响大，所以是一行一行的数
         */
    }

    // TODO ERROR，这种校验是不合理的，仅仅校验了现有数独是否合法，没办法校验是否能正常填充
    public boolean isValidSudoku(char[][] board) {
        Map<Character, Boolean>[] row = new HashMap[9];
        Map<Character, Boolean>[] column = new HashMap[9];
        Map<Character, Boolean>[] box = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            row[i] = new HashMap<>();
            column[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                // 如果可以取到
                if (row[i].getOrDefault(board[i][j], false)) {
                    return false;
                }
                if (column[i].getOrDefault(board[i][j], false)) {
                    return false;
                }
                // 第几个9宫格；是横着数的，以行的比例，就是一行一行数
                int boxIdx = i / 3 * 3 + j / 3;
                if (box[boxIdx].getOrDefault(board[i][j], false)) {
                    return false;
                }
                row[i].put(board[i][j], true);
                column[i].put(board[i][j], true);
                box[i].put(board[i][j], true);
            }
        }
        return true;
    }
}
