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

    private static final int TOTAL_A = 656255;
    //    5951.45
    private static final int TOTAL_B = 614750;

    //   月RATE = 年RATE / 12
    private static final BigDecimal MONTHLY_RATE_A = MathUtil.divide(0.032, 12, 10);
    private static final BigDecimal MONTHLY_RATE_B = MathUtil.divide(0.026, 12, 10);

    private static int aRemainPeriod() {
        return 346 - monthPeriod();
    }

    private static int bRemainPeriod() {
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
    public void totalMoney() {
        float a = 3078.49F;
        float b = 2609.49F;
        System.out.println(STR."all total sum: \{a * aRemainPeriod() + b * bRemainPeriod()}");
        System.out.println(STR."next total sum: \{a * aRemainPeriod() + b * bRemainPeriod() - a - b}");
        System.out.println(STR."principal sum: \{TOTAL_A + TOTAL_B}");
        System.out.println(STR."month sum: \{a + b}");
        System.out.println(STR."business interest \{a * aRemainPeriod() - TOTAL_A}");
        System.out.println(STR."fund interest \{b * bRemainPeriod() - TOTAL_B}");
        float v = a * (TOTAL_A - 100000) / TOTAL_A;
        System.out.println(v);
        System.out.println(a - v);
    }

    @Test
    public void testMonthA() {
//        3.2
        System.out.println(calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, aRemainPeriod()));
    }

    @Test
    public void testMonthB() {
        // 2.6
        System.out.println(calculateMonthlyPayment(TOTAL_B, MONTHLY_RATE_B, bRemainPeriod()));
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
        LocalDate sleep = LocalDate.of(2026, 5, 30);
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


    /**
     * 计算等额本息月供
     * 公式：月供 = 本金 × 月利率 × (1+r)^n ÷ ((1+r)^n - 1)
     *
     * @param principal   贷款本金
     * @param monthlyRate 月利率
     * @param periods     还款期数
     * @return 月供金额
     */
    private static BigDecimal calculateMonthlyPayment(int principal, BigDecimal monthlyRate, int periods) {
        BigDecimal compoundFactor = calculateCompoundFactor(monthlyRate, periods);
        return BigDecimal.valueOf(principal).multiply(monthlyRate).multiply(compoundFactor).divide(compoundFactor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
    }

    /**
     * 计算复利因子 (1+r)^n
     *
     * @param monthlyRate 月利率
     * @param periods     还款期数
     * @return 复利因子
     */
    private static BigDecimal calculateCompoundFactor(BigDecimal monthlyRate, int periods) {
        return BigDecimal.ONE.add(monthlyRate).pow(periods);
    }

    /**
     * 计算本月利息
     *
     * @param remainingPrincipal 剩余本金
     * @param monthlyRate        月利率
     * @return 本月利息
     */
    private static BigDecimal calculateMonthlyInterest(BigDecimal remainingPrincipal, BigDecimal monthlyRate) {
        return remainingPrincipal.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 打印等额本息还款明细表头
     */
    private static void printEqualPrincipalHeader(String header) {
        System.out.println(header);
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s%n", "月份", "本月本金", "本月利息", "剩余本金");
    }

    /**
     * 打印一行还款明细
     */
    private static void printEqualPrincipalRow(int month, BigDecimal principalPayment, BigDecimal monthlyInterest, BigDecimal remainingPrincipal) {
        System.out.printf("%-8d %-15.2f %-15.2f %-15.2f%n", month, principalPayment, monthlyInterest, remainingPrincipal);
    }

    @Test
    public void testEqualPrincipal() {
        int periods = aRemainPeriod();
        // 计算月供
        BigDecimal monthlyPayment = calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, periods);
        BigDecimal remainingPrincipal = BigDecimal.valueOf(TOTAL_A);
        BigDecimal totalInterest = BigDecimal.ZERO;
        // 打印表头
        printEqualPrincipalHeader(STR."等额本息计算：贷款总额=\{TOTAL_A}，还款月数=\{periods}，每月还款=\{monthlyPayment}");
        // 逐月计算
        for (int month = 1; month <= periods; month++) {
            BigDecimal monthlyInterest = calculateMonthlyInterest(remainingPrincipal, MONTHLY_RATE_A);
            BigDecimal principalPayment = monthlyPayment.subtract(monthlyInterest);
            remainingPrincipal = remainingPrincipal.subtract(principalPayment).max(BigDecimal.ZERO);
            totalInterest = totalInterest.add(monthlyInterest);
            printEqualPrincipalRow(month, principalPayment, monthlyInterest, remainingPrincipal);
        }
        // 打印汇总
        System.out.println("-----------------------------------------------------------");
        System.out.println(STR."总利息=\{totalInterest}");
    }

    @Test
    public void testEqualPrincipalWithMonthlyEarlyRepayment() {
        int periods = aRemainPeriod();
        BigDecimal monthlyPayment = calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, periods);
        BigDecimal earlyRepayment = BigDecimal.valueOf(10000);
        BigDecimal remainingPrincipal = BigDecimal.valueOf(TOTAL_A);
        BigDecimal totalInterest = BigDecimal.ZERO;
        int actualMonth = 0;
        System.out.println(STR."等额本息+每月提前还款计算：贷款总额=\{TOTAL_A}，原还款月数=\{periods}，每月提前还款=\{earlyRepayment}");
        System.out.println(STR."每月还款=\{monthlyPayment}");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s %-15s%n", "月份", "本月本金", "本月利息", "提前还款", "剩余本金");
        while (remainingPrincipal.compareTo(BigDecimal.ZERO) > 0 && actualMonth < 360) {
            actualMonth++;
            BigDecimal monthlyInterest = calculateMonthlyInterest(remainingPrincipal, MONTHLY_RATE_A);
            BigDecimal principalPayment = monthlyPayment.subtract(monthlyInterest);
            remainingPrincipal = remainingPrincipal.subtract(principalPayment);
            remainingPrincipal = remainingPrincipal.subtract(earlyRepayment);
            totalInterest = totalInterest.add(monthlyInterest);

            System.out.printf("%-8d %-15.2f %-15.2f %-15.2f %-15.2f%n", actualMonth, principalPayment, monthlyInterest, earlyRepayment, remainingPrincipal);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println(STR."实际还款月数=\{actualMonth}，总利息=\{totalInterest}");
    }

    @Test
    public void testEqualPrincipalWithEarlyRepaymentReduceMonths() {
        BigDecimal principal = BigDecimal.valueOf(TOTAL_A);
        int originalPeriods = aRemainPeriod();
        BigDecimal monthlyPayment = calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, originalPeriods);
        BigDecimal earlyRepayment = BigDecimal.valueOf(50000);
        BigDecimal remainingAfterEarly = principal.subtract(earlyRepayment);
        // 计算缩短后的还款期数（利用对数公式）
        double n = Math.log(monthlyPayment.doubleValue() / (monthlyPayment.doubleValue() - remainingAfterEarly.doubleValue() * MONTHLY_RATE_A.doubleValue()))
                / Math.log(1 + MONTHLY_RATE_A.doubleValue());
        int remainingMonths = (int) Math.ceil(n);
        BigDecimal remainingPrincipal = remainingAfterEarly;
        BigDecimal totalInterest = BigDecimal.ZERO;
        System.out.println(STR."等额本息+一次性提前还款计算：贷款总额=\{TOTAL_A}，原还款月数=\{originalPeriods}，提前还款=\{earlyRepayment}");
        System.out.println(STR."剩余本金=\{remainingPrincipal}，原月供=\{monthlyPayment.setScale(2, RoundingMode.HALF_UP)}，缩短后期数=\{remainingMonths}");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s%n", "月份", "本月本金", "本月利息", "剩余本金");
        for (int month = 1; month <= remainingMonths; month++) {
            BigDecimal monthlyInterest = calculateMonthlyInterest(remainingPrincipal, MONTHLY_RATE_A);
            BigDecimal principalPayment = monthlyPayment.subtract(monthlyInterest);
            remainingPrincipal = remainingPrincipal.subtract(principalPayment);
            totalInterest = totalInterest.add(monthlyInterest);

            System.out.printf("%-8d %-15.2f %-15.2f %-15.2f%n", month, principalPayment, monthlyInterest, remainingPrincipal);
            if (remainingPrincipal.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println(STR."总利息=\{totalInterest}");
        // 计算不提前还款的总利息
        BigDecimal noRepaymentTotalInterest = BigDecimal.ZERO;
        BigDecimal noRepaymentPrincipal = principal;
        for (int month = 1; month <= originalPeriods; month++) {
            BigDecimal monthlyInterest = calculateMonthlyInterest(noRepaymentPrincipal, MONTHLY_RATE_A);
            noRepaymentTotalInterest = noRepaymentTotalInterest.add(monthlyInterest);
            BigDecimal principalPayment = monthlyPayment.subtract(monthlyInterest);
            noRepaymentPrincipal = noRepaymentPrincipal.subtract(principalPayment).max(BigDecimal.ZERO);
        }
        System.out.println(STR."不提前还款总利息=\{noRepaymentTotalInterest}");
        System.out.println(STR."节省利息=\{noRepaymentTotalInterest.subtract(totalInterest)}");
    }
}
