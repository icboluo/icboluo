package com.icboluo.datastructure.algorithm.horse;

import com.icboluo.util.ArrayHelper;
import com.icboluo.util.DateHelper;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author icboluo
 * @date 2020-08-11 12:47
 */
public class HorseChessboard {
    /**
     * 列
     */
    private static int x;
    /**
     * 行
     */
    private static int y;

    private static boolean[] visited;

    private static boolean finished;

    public static void main(String[] args) {
        x = 8;
        y = 8;
        int row = 1;
        int column = 1;
        int[][] chessboard = new int[x][y];
        visited = new boolean[x * y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        DateHelper.parseTimeStampToSecond(end - start);
        ArrayHelper.print(chessboard);
    }

    /**
     * 马踏棋盘算法
     *
     * @param chessboard 棋盘
     * @param row        当前位置的行从0开始
     * @param column     当前位置的列从0开始
     * @param step       第几步，初始1
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        //一位数组表示二维数组
        visited[row * x + column] = true;
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if (!visited[p.y * x + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        if (step < x * y && !finished) {
            chessboard[row][column] = 0;
            visited[row * x + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 获得下一个节点的位置
     *
     * @param curPoint 当前节点的位置
     * @return 下一个节点位置集合
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        boolean b0 = (p1.x = curPoint.x + 2) < x && (p1.y = curPoint.y - 1) >= 0;

        if (b0) {
            ps.add(new Point(p1));
        }
        boolean b1 = (p1.x = curPoint.x + 2) < x && (p1.y = curPoint.y + 1) < y;
        if (b1) {
            ps.add(new Point(p1));
        }
        boolean b2 = (p1.x = curPoint.x + 1) < x && (p1.y = curPoint.y + 2) < y;
        if (b2) {
            ps.add(new Point(p1));
        }
        boolean b3 = (p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < y;
        if (b3) {
            ps.add(new Point(p1));
        }
        boolean b4 = (p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < y;
        if (b4) {
            ps.add(new Point(p1));
        }
        boolean b5 = (p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0;
        if (b5) {
            ps.add(new Point(p1));
        }
        boolean b6 = (p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0;
        if (b6) {
            ps.add(new Point(p1));
        }
        boolean b7 = (p1.x = curPoint.x + 1) < x && (p1.y = curPoint.y - 2) >= 0;
        if (b7) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void sort(ArrayList<Point> ps) {
        ps.sort((o1, o2) -> {
            int count1 = next(o1).size();
            int count2 = next(o2).size();
            return count1 - count2;
        });
    }
}
