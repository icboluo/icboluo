package com.icboluo.datastructure;

import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author icboluo
 * @since 2025-03-10 8:37
 */
class ToolTest {
    @Test
    public void time() {
        float contribute = 10.123F;
        float avg = 8.456F;

        BigDecimal workDay = MathUtil.divide(contribute, avg - 8, 1);
        System.out.println(STR."workDay = \{workDay}");

        float target = 9F;
        float excess = contribute - (target - 8) * workDay.floatValue();
        System.out.println(STR."excess = \{excess}");

        int a1 = 3;
        int b1 = 1;
        float excess1 = a1 * (9.65F - target) - b1 * (target - 7F) + excess;
        System.out.println(STR."excess1 = \{excess1}");

        int a2 = 2;
        int b2 = 2;
        float excess2 = a2 * (9.65F - target) - b2 * (target - 7F) + excess;
        System.out.println(STR."excess2 = \{excess2}");
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
    public void testMon1() {
        // 3.3 ----> 3.2
        BigDecimal rn = BigDecimal.valueOf(0.033);
        BigDecimal r = rn.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal r30 = r.add(BigDecimal.ONE).pow(360 - monthPeriod());
        BigDecimal z = r.multiply(r30).divide(r30.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_DOWN);
        System.out.println((z.multiply(BigDecimal.valueOf(685099))));
    }

    @Test
    public void testMon2() {
        // 2.85 ----> 2.6
        BigDecimal rn = BigDecimal.valueOf(0.0285);
        BigDecimal r = rn.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal r30 = r.add(BigDecimal.ONE).pow(360 - monthPeriod());
        BigDecimal z = r.multiply(r30).divide(r30.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_DOWN);
        System.out.println((z.multiply(BigDecimal.valueOf(624760))));
    }

    private static int monthPeriod() {
        LocalDate start = LocalDate.of(2024, 1, 20);
        LocalDate now = LocalDate.now();
        Period period = Period.between(start, now);
        return period.getMonths() + period.getYears() * 12 + 1;
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
        LocalDate sleep = LocalDate.of(2025, 10, 26);
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
