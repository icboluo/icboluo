package com.icboluo.datastructure;

import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author icboluo
 * @since 2025-03-10 8:37
 */
class ToolTest {

    @Test
    public void test1() {
        double monthAvg = 8.41;
        double contribute = 4;
        int totalDay = MathUtil.divide(contribute, monthAvg - 8, 0).intValue();
        double[] arr = {8};
        double sum = Arrays.stream(arr).sum();

        int workDay = totalDay - arr.length;

        System.out.println(STR."work day = \{workDay}, overwork day = \{arr.length}");

        double totalWorkTime = totalDay * monthAvg - sum;
        double avg = MathUtil.divide(totalWorkTime, workDay, 3).doubleValue();

        System.out.println(STR."avg = \{avg}");
        System.out.println(STR."total week overwork time = \{(BigDecimal.valueOf(avg - 8)).multiply(BigDecimal.valueOf(workDay)).setScale(3, RoundingMode.HALF_DOWN)}");

        double today1 = 7;
        System.out.println(STR."today low avg = \{MathUtil.divide(totalWorkTime + today1, workDay + 1, 3)}");
        double today2 = 9.65;
        System.out.println(STR."today up avg = \{MathUtil.divide(totalWorkTime + today2, workDay + 1, 3)}");

    }

    @Test
    public void test2() {
        float a = 3115.4F;
        float b = 2690.59F;
        float sum = a + b;
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
        LocalDate sleep = LocalDate.of(2025, 6, 5);
        for (int i = 0; i < 10; i++) {
            sleep = printSleepDate(sleep);
        }
    }

    private void cal3(double[][] operation, double cur) {
        int count = 0;
        double total = 0;
        for (double[] doubles : operation) {
            count += (int) doubles[1];
            total += doubles[0] * doubles[1];
        }
        System.out.println(STR."实际获取: \{total}");
        System.out.println(STR."最大获取: \{cur * count}");
        System.out.println(STR."相对亏损 : \{cur * count - total}");
    }

    private LocalDate printSleepDate(LocalDate sleep) {
        System.out.print(STR."\{sleep}, ");
        System.out.print(STR."\{sleep.plusDays(1)}, ");
        System.out.print(STR."\{sleep.plusDays(2)}, ");
        System.out.print(STR."\{sleep.plusDays(3)}");
        System.out.println();
        return sleep.plusDays(6);
    }
}
