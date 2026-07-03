package com.icboluo.datastructure;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author icboluo
 * @since 2026-07-03 22:28
 */
class ConsumerTest {

    @Test
    public void testCityMonthlyYearlyCost() {
        CostItem[] items = new CostItem[]{
                new CostItem("餐饮", BigDecimal.valueOf(1000)),
                new CostItem("交通", BigDecimal.valueOf(100)),
                new CostItem("停车费", BigDecimal.valueOf(200)),
                new CostItem("水+燃气", BigDecimal.valueOf(50)),
                new CostItem("电费", BigDecimal.valueOf(120)),
                new CostItem("通讯", BigDecimal.valueOf(70)),
                new CostItem("物业费", BigDecimal.valueOf(246)),
                new CostItem("贷款", BigDecimal.valueOf(5688)),
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
