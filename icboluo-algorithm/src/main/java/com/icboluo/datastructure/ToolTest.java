package com.icboluo.datastructure;

import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author icboluo
 * @since 2025-03-10 8:37
 */
class ToolTest {

    @Test
    public void test1() {
        double gongXian = 4;
        double monthAvg = 8.41;
        int totalDay = MathUtil.divide(gongXian, monthAvg - 8, 0).intValue();
        double[] arr = {8};

        System.out.println(STR."total day = \{totalDay}");

        double sum = Arrays.stream(arr).sum();
        BigDecimal avg = MathUtil.divide(totalDay * monthAvg - sum, totalDay - arr.length);
        System.out.println(STR."avg = \{avg}");
    }

    @Test
    public void test2() {
        double a = 3115.4;
        double b = 2690.59;
        double sum = a + b;
        System.out.println(STR."sum: \{sum}");
        System.out.println("利息 " + (a * 360 - 710000));
        System.out.println("利息 " + (b * 360 - 650000));
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
        System.out.println("亏损: " + (cur * count - total));
    }

    private LocalDate printSleepDate(LocalDate sleep) {
        System.out.print(sleep + ", ");
        System.out.print(sleep.plusDays(1) + ", ");
        System.out.print(sleep.plusDays(2) + "");
        System.out.println();
        return sleep.plusDays(6);
    }
}
