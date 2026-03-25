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

    private static final Long TOTAL_A = 678849L;
    private static final Long TOTAL_B = 618566L;

    private static int remainPeriod() {
        return 360 - monthPeriod();
    }

    private static int monthPeriod() {
        LocalDate start = LocalDate.of(2024, 1, 20);
        LocalDate now = LocalDate.now();
        Period period = Period.between(start, now);
        return period.getMonths() + period.getYears() * 12 + 1;
    }

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
        float a = 3078.49F;
        float b = 2609.49F;
        System.out.println(STR."total sum: \{TOTAL_A + TOTAL_B}");
        System.out.println(STR."sum: \{a + b}");
        System.out.println(STR."total interest \{a * remainPeriod() - TOTAL_A}");
        System.out.println(STR."total interest \{b * remainPeriod() - TOTAL_B}");
        float v = a * (TOTAL_A - 100000) / TOTAL_A;
        System.out.println(v);
        System.out.println(a - v);
    }


    @Test
    public void testMon1() {
        // 3.2
        BigDecimal rn = BigDecimal.valueOf(0.032);
        BigDecimal r = rn.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        // (1+r)^n （1+月利率）^月数
        BigDecimal r30 = BigDecimal.ONE.add(r).pow(remainPeriod());
        BigDecimal z = r.multiply(r30).divide(r30.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_DOWN);
        System.out.println((z.multiply(BigDecimal.valueOf(TOTAL_A))));
    }

    @Test
    public void testMon2() {
        // 2.6
        BigDecimal rn = BigDecimal.valueOf(0.026);
        BigDecimal r = rn.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal r30 = BigDecimal.ONE.add(r).pow(remainPeriod());
        BigDecimal z = r.multiply(r30).divide(r30.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_DOWN);
        System.out.println((z.multiply(BigDecimal.valueOf(TOTAL_B))));
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
        LocalDate sleep = LocalDate.of(2026, 1, 18);
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
