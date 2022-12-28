package com.icboluo.leetcode.after_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-12-28 21:04
 */
class N1260_移位二维数组 {
    public static void main(String[] args) {
        N1260_移位二维数组 cla = new N1260_移位二维数组();
        int[][] arr = new int[][]{{1, 2, 3},  // ---------------------
                {4, 5, 6},// --------------------------
                {7, 8, 9},};
        List<List<Integer>> lists = cla.shiftGrid(arr, 1);
        System.out.println("lists = " + lists);
    }

    /**
     * 试图在原有二维数组基础上进行交换，可惜难以处理
     *
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            res.add(new ArrayList<>());
        }
        // 尺寸
        int dimension = row * col;
        k = k % dimension;
        int begin = dimension - k;
        // 总体逻辑是2个指针，一个指向旧指针（begin->dimension+begin），一个指向新指针（count）；将值一个一个copy即可
        int count = 0;
        for (int i = begin; i < dimension + begin; i++) {
            // 判断当前元素是第几行；%row只是用来修正的
            int r = i / col % row;
            // 判断是多少列
            int c = i % col;
            // 第几行
            res.get(count++ / col).add(grid[r][c]);
        }
        return res;
    }
}
