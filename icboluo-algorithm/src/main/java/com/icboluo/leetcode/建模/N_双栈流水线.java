package com.icboluo.leetcode.建模;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2024-03-06 22:49
 */
public class N_双栈流水线 {
    public static void main(String[] args) {
        int[] car = {4, 2, 5};
        int[][] order = {
                {0, 0},// -------------------
                {4, 2},// -------------------
                {5, 2},// -------------------
                {6, 2},// -------------------
                {7, 2},// -------------------
        };
        List<int[]> res = cal(car, order, 3);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }

    /**
     * 双堆解决，互通有无，平衡双堆,此问题也是时间轴问题
     *
     * @param car 可以生产3种车，每种车的花费时间
     * @param orders 订单个数[订单产生时间，订单车]
     * @param line 多少条流水线
     * @return [流水线号，交付时间]
     */
    private static List<int[]> cal(int[] car, int[][] orders, int line) {
        List<int[]> res = new ArrayList<>();
        // 空闲流水线号
        PriorityQueue<Integer> pqIdle = new PriorityQueue<>();
        // 繁忙流水线号，完成时间
        PriorityQueue<int[]> pqBusy = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < line; i++) {
            pqIdle.offer(i);
        }
        for (int[] order : orders) {
            int curTime = order[0];
            // 繁忙堆入空闲堆
            while (!pqBusy.isEmpty() && pqBusy.peek()[1] <= curTime) {
                pqIdle.offer(pqBusy.poll()[0]);
            }
            int customTime = car[order[1]];
            // 如果有空闲流水线
            if (!pqIdle.isEmpty()) {
                Integer poll = pqIdle.poll();
                int[] temp = {poll, curTime + customTime};
                pqBusy.offer(temp);
                res.add(temp);
            } else {
                // 没有空闲的就是用繁忙的
                int[] poll = pqBusy.poll();
                assert poll != null;
                int[] temp = {poll[0], poll[1] + customTime};
                pqBusy.offer(temp);
                res.add(temp);
            }
        }
        return res;
    }
}
