package com.icboluo.datastructure.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 */
class Queen8 {
    public static void main(String[] args) {
        var cla = new Queen8();
        System.out.println(cla.solveNQueens1(8));
        System.out.println(cla.solveNQueens2(8));
    }

    /**
     * 棋盘
     */
    boolean[][] chess;

    List<List<String>> res;

    /**
     * N0051 8皇后问题
     *
     * @param n 矩阵行列
     * @return [".Q..","...Q","Q...","..Q."] list 里面的一个元素代表一张图
     */
    public List<List<String>> solveNQueens(int n) {
        return solveNQueens2(n);
    }

    public List<List<String>> solveNQueens1(int n) {
        chess = new boolean[n][n];
        res = new ArrayList<>();
        backtrack1(0);
        return res;
    }

    public List<List<String>> solveNQueens2(int n) {
        array = new int[n];
        res = new ArrayList<>();
        backtrack2(0);
        return res;
    }

    /**
     * @param row 当前处理的行
     */
    private void backtrack1(int row) {
        // 触发结束条件
        if (row == chess.length) {
            List<String> track = Arrays.stream(chess).map(a -> {
                StringBuilder str = new StringBuilder();
                for (boolean b : a) {
                    str.append(b ? "Q" : ".");
                }
                return str.toString();
            }).toList();
            res.add(track);
            return;
        }
        for (int col = 0; col < chess[row].length; col++) {
            // 剔除不合法选择
            if (!isValid(row, col)) {
                continue;
            }
            // 做选择，在这里路径直接记录到棋盘中，row只是代表被处理到第几行，所以这里的选择相对简单
            chess[row][col] = true;
            backtrack1(row + 1);
            chess[row][col] = false;
        }
    }

    /**
     * 因为是一行一行的记录，所以不需要判断下方是否有皇后
     *
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(int row, int col) {
        int n = chess.length;
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (chess[i][col]) {
                return false;
            }
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chess[i][j]) {
                return false;
            }
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 结果集
     */
    int[] array;

    /**
     * 回溯会产生所有的结果，因为是check递归，所以每次进入到check中都有  for (int i = 0; i < max; i++) {
     * 会将摆放位置后移再次运算
     *
     * @param n
     */
    private void backtrack2(int n) {
        if (n == array.length) {
            List<String> temp = new ArrayList<>();
            for (int value : array) {
                String[] tempArr = new String[array.length];
                Arrays.fill(tempArr, ".");
                tempArr[value] = "Q";
                String str = String.join("", tempArr);
                temp.add(str);
            }
            res.add(temp);
            return;
        }
        for (int i = 0; i < array.length; i++) {
            // 做选择
            array[n] = i;
            // 剔除不合法选择
            if (judge(n)) {
                // 执行下一层决策树...这里用的是int记录单个数据，下次再使用的时候会直接覆盖，所以不需要撤销
                backtrack2(n + 1);
            }
            // array[n]=before 此处不需要撤销选择
        }
    }

    /**
     * @param n 表示放置第n个皇后
     * @return 是否和前面的皇后冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //在同一列或者同一斜线
            boolean b = array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i]);
            if (b) {
                return false;
            }
        }
        return true;
    }
}
