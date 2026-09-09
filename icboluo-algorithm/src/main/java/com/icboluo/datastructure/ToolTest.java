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

    //    1714811 / 827 = 2073
    private static final int TOTAL_A = 604794;
    private static final int TOTAL_B = 612192;

    //   月RATE = 年RATE / 12
    private static final BigDecimal MONTHLY_RATE_A = MathUtil.divide(0.032, 12, 10);
    private static final BigDecimal MONTHLY_RATE_B = MathUtil.divide(0.026, 12, 10);

    private static final LocalDate LOAN_START = LocalDate.of(2024, 1, 21);

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
        System.out.println(STR."all total sum: \{a * aRemainPeriod() + b * bRemainPeriod()}, next: \{a * aRemainPeriod() + b * bRemainPeriod() - a - b}");
        System.out.println(STR."principal sum: \{TOTAL_A + TOTAL_B}");
        System.out.println(STR."month sum: \{a + b}, \{a + b - 1400}");
        System.out.println(STR."business interest \{a * aRemainPeriod() - TOTAL_A}");
        System.out.println(STR."fund interest \{b * bRemainPeriod() - TOTAL_B}");
        float v = a * (TOTAL_A - 100000) / TOTAL_A;
        float v2 = b * (TOTAL_B - 100000) / TOTAL_B;
        System.out.println(STR."\{v}, \{a - v}");
        System.out.println(STR."\{v2}, \{b - v2}");
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
            System.out.printf("%-8d %-15.2f %-15.2f %-15.2f%n", month, principalPayment, monthlyInterest, remainingPrincipal);
        }
        // 打印汇总
        System.out.println("-----------------------------------------------------------");
        System.out.println(STR."总利息=\{totalInterest}");
    }

    /**
     * 打印等额本息还款明细表头
     */
    private static void printEqualPrincipalHeader(String header) {
        System.out.println(header);
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-7s %-12s %-13s %-15s%n", "月份", "本月本金", "本月利息", "剩余本金");
    }

    @Test
    public void testEqualPrincipalBoth() {
        int aPeriods = aRemainPeriod();
        int bPeriods = bRemainPeriod();
        BigDecimal aPayment = LoanUtil.calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, aPeriods);
        BigDecimal bPayment = LoanUtil.calculateMonthlyPayment(TOTAL_B, MONTHLY_RATE_B, bPeriods);

        BigDecimal aRemaining = BigDecimal.valueOf(TOTAL_A);
        BigDecimal bRemaining = BigDecimal.valueOf(TOTAL_B);
        BigDecimal aInterestTotal = BigDecimal.ZERO;
        BigDecimal bInterestTotal = BigDecimal.ZERO;
        // 整笔贷款的总利息（固定值，非累计）
        BigDecimal aTotalInterestFull = LoanUtil.calculateTotalInterest(TOTAL_A, MONTHLY_RATE_A, aPayment, aPeriods);
        BigDecimal bTotalInterestFull = LoanUtil.calculateTotalInterest(TOTAL_B, MONTHLY_RATE_B, bPayment, bPeriods);

        System.out.printf("%-6s | %-12s %-12s %-14s | %-12s %-12s %-14s | %-12s %-12s %-14s %-13s %-13s%n",
                "月份", "A本金", "A利息", "A剩余本金", "B本金", "B利息", "B剩余本金",
                "总本金", "总利息", "总剩余本金", "总剩余利息", "总剩余本息");
        System.out.println("-".repeat(149));
        int maxPeriods = Math.max(aPeriods, bPeriods);
        for (int month = 1; month <= maxPeriods; month++) {
            // 贷款 A 当月
            String aP = "-", aI = "-", aR = "-";
            BigDecimal aPrin = BigDecimal.ZERO;
            BigDecimal aInt = BigDecimal.ZERO;
            if (month <= aPeriods) {
                aInt = LoanUtil.calculateMonthlyInterest(aRemaining, MONTHLY_RATE_A);
                aPrin = aPayment.subtract(aInt);
                aRemaining = aRemaining.subtract(aPrin).max(BigDecimal.ZERO);
                aInterestTotal = aInterestTotal.add(aInt);
                aP = aPrin.setScale(2, RoundingMode.HALF_UP).toString();
                aI = aInt.setScale(2, RoundingMode.HALF_UP).toString();
                aR = aRemaining.setScale(2, RoundingMode.HALF_UP).toString();
            }
            // 贷款 B 当月
            String bP = "-", bI = "-", bR = "-";
            BigDecimal bPrin = BigDecimal.ZERO;
            BigDecimal bInt = BigDecimal.ZERO;
            if (month <= bPeriods) {
                bInt = LoanUtil.calculateMonthlyInterest(bRemaining, MONTHLY_RATE_B);
                bPrin = bPayment.subtract(bInt);
                bRemaining = bRemaining.subtract(bPrin).max(BigDecimal.ZERO);
                bInterestTotal = bInterestTotal.add(bInt);
                bP = bPrin.setScale(2, RoundingMode.HALF_UP).toString();
                bI = bInt.setScale(2, RoundingMode.HALF_UP).toString();
                bR = bRemaining.setScale(2, RoundingMode.HALF_UP).toString();
            }
            // a、b 同一行展示 + 合计列
            // 总本金=当月a+b本金；总利息=当月a+b利息；总剩余本金=当月a+b剩余
            // 总剩余利息=整笔总利息-已还累计利息；总剩余本息=总剩余本金+总剩余利息
            BigDecimal sumPrincipal = aPrin.add(bPrin);
            BigDecimal sumInterest = aInt.add(bInt);
            BigDecimal sumRemaining = aRemaining.add(bRemaining);
            BigDecimal sumRemainingInterest = aTotalInterestFull.subtract(aInterestTotal)
                    .add(bTotalInterestFull.subtract(bInterestTotal));
            BigDecimal sumRemainingPrincipalInterest = sumRemaining.add(sumRemainingInterest);
            System.out.printf("%-6d | %-14s %-14s %-16s | %-14s %-14s %-16s | %-14s %-14s %-16s %-16s %-16s%n",
                    month, aP, aI, aR, bP, bI, bR,
                    sumPrincipal.setScale(2, RoundingMode.HALF_UP),
                    sumInterest.setScale(2, RoundingMode.HALF_UP),
                    sumRemaining.setScale(2, RoundingMode.HALF_UP),
                    sumRemainingInterest.setScale(2, RoundingMode.HALF_UP),
                    sumRemainingPrincipalInterest.setScale(2, RoundingMode.HALF_UP));
        }
        System.out.println("-".repeat(85));
        System.out.println(STR."贷款A 总本金=\{TOTAL_A}，总利息=\{aInterestTotal.setScale(2, RoundingMode.HALF_UP)}");
        System.out.println(STR."贷款B 总本金=\{TOTAL_B}，总利息=\{bInterestTotal.setScale(2, RoundingMode.HALF_UP)}");
        System.out.println(STR."两笔贷款总计本金=\{TOTAL_A + TOTAL_B}，总计利息=\{aInterestTotal.add(bInterestTotal).setScale(2, RoundingMode.HALF_UP)}");
    }

    @Test
    public void testMonthlyEarlyRepayment() {
        int periods = aRemainPeriod();
        BigDecimal monthlyPayment = LoanUtil.calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, periods);
        BigDecimal earlyRepayment = BigDecimal.valueOf(10000);
        BigDecimal remainingPrincipal = BigDecimal.valueOf(TOTAL_A);
        BigDecimal totalInterest = BigDecimal.ZERO;
        int actualMonth = 0;
        System.out.println(STR."等额本息+每月提前还款计算：贷款总额=\{TOTAL_A}，原还款月数=\{periods}，每月提前还款=\{earlyRepayment}");
        System.out.println(STR."每月还款=\{monthlyPayment}");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-7s %-12s %-13s %-13s %-13s%n", "月份", "本月本金", "本月利息", "提前还款", "剩余本金");
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
    public void testEarlyRepaymentReduceMonths() {
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
        System.out.printf("%-7s %-13s %-13s %-13s%n", "月份", "本月本金", "本月利息", "剩余本金");
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
                CostItem.ofYearly("取暖费", BigDecimal.valueOf(2500)),
                new CostItem("电费", BigDecimal.valueOf(200)),
                new CostItem("通讯", BigDecimal.valueOf(80)),
                new CostItem("物业费", BigDecimal.valueOf(246)),
                new CostItem("贷款A", BigDecimal.valueOf(3078)),
                new CostItem("贷款B", BigDecimal.valueOf(1200)),
        };
        printCost(items);
    }

    @Test
    public void testEarlyRepaymentReducePayment() {
        // ===== 提前还款【降低月供】方式：提前还一笔，剩余期数不变，重新计算月供，以后每月月供变少 =====
        BigDecimal principal = BigDecimal.valueOf(TOTAL_A);
        int originalPeriods = aRemainPeriod();
        // 原月供（剩余期数不变）
        BigDecimal originalMonthlyPayment = LoanUtil.calculateMonthlyPayment(TOTAL_A, MONTHLY_RATE_A, originalPeriods);
        // 提前还款金额
        BigDecimal earlyRepayment = BigDecimal.valueOf(7000);
        // 提前还款后剩余本金
        BigDecimal remainingAfterEarly = principal.subtract(earlyRepayment);
        // 剩余期数不变，重新计算月供（降低后的月供）
        BigDecimal reducedMonthlyPayment = LoanUtil.calculateReducedMonthlyPayment(remainingAfterEarly, MONTHLY_RATE_A, originalPeriods);
        BigDecimal monthlyReduce = originalMonthlyPayment.subtract(reducedMonthlyPayment);

        System.out.println("===== 提前还款-降低月供方式 =====");
        System.out.printf("贷款总额=%.2f，原还款月数=%d，原月供=%.2f%n", principal, originalPeriods, originalMonthlyPayment);
        System.out.printf("提前还款=%.2f，剩余本金=%.2f，新月供=%.2f，每月少还=%.2f%n",
                earlyRepayment, remainingAfterEarly, reducedMonthlyPayment, monthlyReduce);
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-7s %-13s %-13s %-13s%n", "月份", "本月本金", "本月利息", "剩余本金");

        // 逐月计算降低月供后的还款明细
        BigDecimal remainingPrincipal = remainingAfterEarly;
        BigDecimal totalInterest = BigDecimal.ZERO;
        for (int month = 1; month <= originalPeriods; month++) {
            BigDecimal monthlyInterest = LoanUtil.calculateMonthlyInterest(remainingPrincipal, MONTHLY_RATE_A);
            BigDecimal principalPayment = reducedMonthlyPayment.subtract(monthlyInterest);
            // 最后一期本金补齐，避免剩余本金为负
            if (remainingPrincipal.compareTo(principalPayment) < 0) {
                principalPayment = remainingPrincipal;
            }
            remainingPrincipal = remainingPrincipal.subtract(principalPayment).max(BigDecimal.ZERO);
            totalInterest = totalInterest.add(monthlyInterest);
            System.out.printf("%-8d %-15.2f %-15.2f %-15.2f%n", month, principalPayment, monthlyInterest, remainingPrincipal);
            if (remainingPrincipal.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println(STR."降低月供方式总利息=\{totalInterest.setScale(2, RoundingMode.HALF_UP)}");

        // 对比：不提前还款的总利息
        BigDecimal noRepaymentTotalInterest = LoanUtil.calculateTotalInterest(TOTAL_A, MONTHLY_RATE_A, originalMonthlyPayment, originalPeriods);
        System.out.println(STR."不提前还款总利息=\{noRepaymentTotalInterest.setScale(2, RoundingMode.HALF_UP)}");
        System.out.println(STR."节省利息=\{noRepaymentTotalInterest.subtract(totalInterest).setScale(2, RoundingMode.HALF_UP)}");
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

    public record CostItem(String name, BigDecimal monthlyAmount, BigDecimal yearlyAmount) {

        public CostItem(String name, BigDecimal monthlyAmount) {
            this(name, monthlyAmount, monthlyAmount.multiply(BigDecimal.valueOf(12)));
        }

        /**
         * 按年金额构造，月金额按 12 个月均摊（保留 2 位小数）
         */
        public static CostItem ofYearly(String name, BigDecimal yearlyAmount) {
            return new CostItem(name, yearlyAmount.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP), yearlyAmount);
        }
    }
}
