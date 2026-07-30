package com.icboluo.datastructure;

import com.icboluo.util.LoanUtil;
import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * @author icboluo
 * @since 2025-03-10 8:37
 */
class ToolTest {

    private static final int TOTAL_A = 606255;
    //    5951.45
    private static final int TOTAL_B = 613474;

    //   月RATE = 年RATE / 12
    private static final BigDecimal MONTHLY_RATE_A = MathUtil.divide(0.032, 12, 10);
    private static final BigDecimal MONTHLY_RATE_B = MathUtil.divide(0.026, 12, 10);

    private static final LocalDate LOAN_START = LocalDate.of(2024, 1, 20);

    private static int aRemainPeriod() {
        return LoanUtil.remainPeriod(LOAN_START, 311);
    }

    private static int bRemainPeriod() {
        return LoanUtil.remainPeriod(LOAN_START, 360);
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
    public void testMonth() {
//        3.2
        System.out.println(LoanUtil.calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, aRemainPeriod()));
        // 2.6
        System.out.println(LoanUtil.calculateMonthlyPayment(TOTAL_B, MONTHLY_RATE_B, bRemainPeriod()));
    }

    @Test
    public void earnings() {
        double[][] arr1 = {
                {1.499, 3000},// --------------------
                {1.501, 3000},// --------------------
        };
        double cur1 = 1.500;
        calEarnings(arr1, cur1);
    }

    private void calEarnings(double[][] operation, double cur) {
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
        BigDecimal monthlyPayment = LoanUtil.calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, periods);
        BigDecimal remainingPrincipal = BigDecimal.valueOf(TOTAL_A);
        BigDecimal totalInterest = BigDecimal.ZERO;
        // 打印表头
        printEqualPrincipalHeader(STR."等额本息计算：贷款总额=\{TOTAL_A}，还款月数=\{periods}，每月还款=\{monthlyPayment}");
        // 逐月计算
        for (int month = 1; month <= periods; month++) {
            BigDecimal monthlyInterest = LoanUtil.calculateMonthlyInterest(remainingPrincipal, MONTHLY_RATE_A);
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
        BigDecimal monthlyPayment = LoanUtil.calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, periods);
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
            BigDecimal monthlyInterest = LoanUtil.calculateMonthlyInterest(remainingPrincipal, MONTHLY_RATE_A);
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
        BigDecimal monthlyPayment = LoanUtil.calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, originalPeriods);
        BigDecimal earlyRepayment = BigDecimal.valueOf(50000);
        BigDecimal remainingAfterEarly = principal.subtract(earlyRepayment);
        // 计算缩短后的还款期数（利用对数公式）
        int remainingMonths = LoanUtil.calculateRemainingMonthsAfterEarlyRepayment(monthlyPayment, remainingAfterEarly, MONTHLY_RATE_A);
        BigDecimal remainingPrincipal = remainingAfterEarly;
        BigDecimal totalInterest = BigDecimal.ZERO;
        System.out.println(STR."等额本息+一次性提前还款计算：贷款总额=\{TOTAL_A}，原还款月数=\{originalPeriods}，提前还款=\{earlyRepayment}");
        System.out.println(STR."剩余本金=\{remainingPrincipal}，原月供=\{monthlyPayment.setScale(2, RoundingMode.HALF_UP)}，缩短后期数=\{remainingMonths}");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s%n", "月份", "本月本金", "本月利息", "剩余本金");
        for (int month = 1; month <= remainingMonths; month++) {
            BigDecimal monthlyInterest = LoanUtil.calculateMonthlyInterest(remainingPrincipal, MONTHLY_RATE_A);
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
            BigDecimal monthlyInterest = LoanUtil.calculateMonthlyInterest(noRepaymentPrincipal, MONTHLY_RATE_A);
            noRepaymentTotalInterest = noRepaymentTotalInterest.add(monthlyInterest);
            BigDecimal principalPayment = monthlyPayment.subtract(monthlyInterest);
            noRepaymentPrincipal = noRepaymentPrincipal.subtract(principalPayment).max(BigDecimal.ZERO);
        }
        System.out.println(STR."不提前还款总利息=\{noRepaymentTotalInterest}");
        System.out.println(STR."节省利息=\{noRepaymentTotalInterest.subtract(totalInterest)}");
    }

    @Test
    public void testCityCost() {
        CostItem[] items = new CostItem[]{
                new CostItem("餐饮", BigDecimal.valueOf(1000)),
                new CostItem("交通", BigDecimal.valueOf(100)),
                new CostItem("停车费", BigDecimal.valueOf(200)),
                new CostItem("水+燃气", BigDecimal.valueOf(50)),
                new CostItem("电费", BigDecimal.valueOf(270)),
                new CostItem("通讯", BigDecimal.valueOf(70)),
                new CostItem("物业费", BigDecimal.valueOf(246)),
                new CostItem("贷款", BigDecimal.valueOf(4288)),
        };
        printCost(items);
    }

    private void printCost(CostItem[] items) {
        BigDecimal monthlyTotal = BigDecimal.ZERO;
        BigDecimal yearlyTotal = BigDecimal.ZERO;
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-15s%n", "项目", "月金额", "年金额", "占比");
        System.out.println("-----------------------------------------------------------");
        for (CostItem item : items) {
            monthlyTotal = monthlyTotal.add(item.monthlyAmount());
            yearlyTotal = yearlyTotal.add(item.yearlyAmount());
        }
        for (CostItem item : items) {
            BigDecimal percentage = item.monthlyAmount().divide(monthlyTotal, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
            System.out.printf("%-10s %-15s %-15s %-15s%n", item.name(), item.monthlyAmount(), item.yearlyAmount(), percentage.toString() + "%");
        }
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-15s%n", "合计", monthlyTotal, yearlyTotal, "100%");
    }

    public record CostItem(String name, BigDecimal monthlyAmount) {
        public BigDecimal yearlyAmount() {
            return monthlyAmount.multiply(BigDecimal.valueOf(12));
        }
    }
}
