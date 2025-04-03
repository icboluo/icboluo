package com.icboluo.datastructure;

import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author icboluo
 * @since 2025-03-10 8:37
 */
class ToolTest {

    @Test
    public void test1() {
        double contribute = 4;
        double monthAvg = 8.41;
        int totalDay = MathUtil.divide(contribute, monthAvg - 8, 0).intValue();
        double[] arr = {8};
        double sum = Arrays.stream(arr).sum();

        int workDay = totalDay - arr.length;

        System.out.println(STR."work day = \{totalDay}, overwork day = \{arr.length}");

        double totalWorkTime = totalDay * monthAvg - sum;
        double avg = MathUtil.divide(totalWorkTime, workDay, 2).doubleValue();

        System.out.println(STR."avg = \{avg}");
        System.out.println(STR."total week overwork time = \{(avg - 8) * workDay}");
    }

    @Test
    public void test2() {
        double a = 3115.4;
        double b = 2690.59;
        double sum = a + b;
        System.out.println(STR."sum: \{sum}");
        System.out.println(STR."total interest \{a * 360 - 710000}");
        System.out.println(STR."total interest \{b * 360 - 650000}");
        System.out.println(a - a * 61 / 71);
        System.out.println(a * 61 / 71);
    }

    @Test
    public void test3() {
        double[][] arr1 = {
                {1.499, 3000},// --------------------
                {1.501, 3000},// --------------------
        };
        double cur1 = 1.500;
        cal3(arr1, cur1);
    }


    @Test
    public void test4() {
        LocalDate sleep = LocalDate.of(2025, 3, 8);
        for (int i = 0; i < 10; i++) {
            sleep = printSleepDate(sleep);
        }
    }

    private void cal3(double[][] operation, double cur) {
        int count = 0;
        double total = 0;
        for (int i = 0; i < operation.length; i++) {
            count += (int) operation[i][1];
            total += operation[i][0] * operation[i][1];
        }
        System.out.println("实际获取: " + total);
        System.out.println("最大获取: " + cur * count);
        System.out.println("相对亏损 : " + (cur * count - total));
    }

    private LocalDate printSleepDate(LocalDate sleep) {
        System.out.print(sleep + ", ");
        System.out.print(sleep.plusDays(1) + ", ");
        System.out.print(sleep.plusDays(2) + "");
        System.out.println();
        return sleep.plusDays(6);
    }
}
