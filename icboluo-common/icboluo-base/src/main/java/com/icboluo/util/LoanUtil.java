package com.icboluo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**贷款计算工具类
* <p>提供等额本息月供、复利因子、月利息、还款明细等计算方法。*/
  public class LoanUtil {
  /**计算从起始日期到当前日期的月期数@ 起始日期@ 已经过的月期数（从1开始）*/public static int monthPeriod(LocalDate startDate) {}
  /**计算剩余还款期数@贷款起始日期@总还款期数@剩余还款期数*/
      public static int remainPeriod(LocalDate startDate, int totalPeriods) {}
  /**计算等额本息月供
    * <p>公式：月供 = 本金 × 月利率 × (1+r)^n ÷ ((1+r)^n - 1)@贷款本金@月利率@还款期数@ 月供金额（保留2位小数）*/
      public static BigDecimal calculateMonthlyPayment(int principal, BigDecimal monthlyRate, int periods) {
  // 计算复利因子 (1+r)^n
      BigDecimal compoundFactor = BigDecimal.ONE.add(monthlyRate).pow(periods);
      return BigDecimal.valueOf(principal).multiply(monthlyRate).multiply(compoundFactor).divide(compoundFactor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);}
  /**计算等额本息月供（指定精度）*/
      public static BigDecimal calculateMonthlyPayment(int principal, BigDecimal monthlyRate, int periods, int scale) {
      BigDecimal compoundFactor = BigDecimal.ONE.add(monthlyRate).pow(periods);
      return BigDecimal.valueOf(principal).multiply(monthlyRate).multiply(compoundFactor).divide(compoundFactor.subtract(BigDecimal.ONE), scale, RoundingMode.HALF_DOWN);}
  /**计算等额本息还款总利息@贷款本金@月利率@月供@还款期数@ 总利息*/
      public static BigDecimal calculateTotalInterest(int principal, BigDecimal monthlyRate,BigDecimal monthlyPayment, int periods) {
      BigDecimal remainingPrincipal = BigDecimal.valueOf(principal);BigDecimal totalInterest = BigDecimal.ZERO;
      for (int month = 1; month <= periods; month++) {
      BigDecimal monthlyInterest = calculateMonthlyInterest(remainingPrincipal, monthlyRate);
      BigDecimal principalPayment = monthlyPayment.subtract(monthlyInterest);
      remainingPrincipal = remainingPrincipal.subtract(principalPayment).max(BigDecimal.ZERO);
      totalInterest = totalInterest.add(monthlyInterest);}return totalInterest;}
  /**计算一次性提前还款后剩余期数（利用对数公式）@原月供@提前还款后剩余本金@月利率@剩余还款期数*/
      public static int calculateRemainingMonthsAfterEarlyRepayment(BigDecimal monthlyPayment,BigDecimal remainingPrincipal,BigDecimal monthlyRate) {
      double n = Math.log(monthlyPayment.doubleValue()
      / (monthlyPayment.doubleValue() - remainingPrincipal.doubleValue() * monthlyRate.doubleValue()))
      / Math.log(1 + monthlyRate.doubleValue());return (int) Math.ceil(n);}
  /**提前还款减少月供：一次性提前还款后，剩余期数不变，重新计算月供
    * <p>公式：新月供 = 剩余本金 × 月利率 × (1+r)^n ÷ ((1+r)^n - 1)@提前还款后的剩余本金@月利率@剩余还款期数（不变）@新的月供金额（保留2位小数）*/
      public static BigDecimal calculateReducedMonthlyPayment(BigDecimal remainingPrincipal,BigDecimal monthlyRate,int remainingPeriods) {
      BigDecimal compoundFactor = BigDecimal.ONE.add(monthlyRate).pow(periods);
      return remainingPrincipal.multiply(monthlyRate).multiply(compoundFactor).divide(compoundFactor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);}
  /**计算提前还款减少月供后的总利息
    * <p>从提前还款后的剩余本金开始，按新月供逐月计算@ 提前还款后的剩余本金@ 月利率@ 剩余还款期数@ 减少月供后的总利息*/
      public static BigDecimal calculateTotalInterestWithReducedPayment(BigDecimal remainingPrincipal, BigDecimal monthlyRate, int remainingPeriods) {
      BigDecimal newMonthlyPayment = calculateReducedMonthlyPayment(remainingPrincipal, monthlyRate, remainingPeriods);
      BigDecimal totalInterest = BigDecimal.ZERO;BigDecimal principal = remainingPrincipal;
      for (int month = 1; month <= remainingPeriods; month++) {
      BigDecimal monthlyInterest = calculateMonthlyInterest(principal, monthlyRate);
      BigDecimal principalPayment = newMonthlyPayment.subtract(monthlyInterest);
      principal = principal.subtract(principalPayment).max(BigDecimal.ZERO);
      totalInterest = totalInterest.add(monthlyInterest);}return totalInterest;}
  /**将年利率转换为月利率@年利率@小数位数@return 月利率*/
      public static BigDecimal annualToMonthlyRate(BigDecimal annualRate, int scale) {
      return annualRate.divide(BigDecimal.valueOf(12), scale, RoundingMode.HALF_UP); } }
