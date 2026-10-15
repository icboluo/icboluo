package com.icboluo.datastructure;

import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author icboluo
 * @since 2026-07-03 22:23
 */
class WorkTimeTest {
    @Test
    public void workTime() {
        // ===== 已知输入（本月统计值）=====
        float contribute = 10F;   // 本月多余的贡献工时（相对 8 小时基准）
        float avg = 9F;           // 每天的平均工时
        float target = 8.5F;            // 平均每天的目标工时

        // 由“多余贡献工时 + 日均工时”反推已经上班的天数（交叉校验用）
        BigDecimal workDay = MathUtil.divide(contribute, avg - 8, 1);
        System.out.println(STR."已经上班的天数 = \{workDay}");

        // ===== 唯一需要手填的输入：这个月总上班天数 =====
        int totalDays = 20;           // 本月总上班天数（直接填这个即可，a、b 会自动算出）

        // 加班天数(a) / 不加班天数(b) 由“总天数 + 日均工时”推导（无需手填）：
        // 约定 加班日=10h、不加班日=7h、基线=8h；a+b=总天数，10a+7b=avg*总天数
        // 解得 a = 总天数*(avg-7)/3，b = 总天数 - a
        float overtimeDays = totalDays * (avg - 7F) / 3F;   // a：加班天数
        float normalDays = totalDays - overtimeDays;        // b：不加班天数
        System.out.println(STR."加班天数 a = \{overtimeDays}，不加班天数 b = \{normalDays}");

        // 目标线以上的多余贡献工时 = 加班日多做的 - 不加班日少做的
        float excess = overtimeDays * (10F - target) - normalDays * (target - 7F);
        System.out.println(STR."本月多余的贡献工时（相对目标） = \{excess}");
    }

    @Test
    public void nightShift() {
        // 晚班排班表（3 天一轮：每天由若干“上班/休息”时段组成）
        record Segment(String day, String period, String status, double hours) {
        }
        Segment[] schedule = {
                new Segment("第1天", "07:00 - 15:00", "休息", 8.0),
                new Segment("第1天", "15:00 - 01:30", "上班", 10.5),
                new Segment("第2天", "01:30 - 07:00", "休息", 5.5),
                new Segment("第2天", "07:00 - 16:30", "上班", 9.5),
                new Segment("第2天", "16:30 - 22:00", "休息", 5.5),
                new Segment("第2天", "22:00 - 06:00", "上班", 8.0),
                new Segment("第3天", "06:00 - 10:30", "休息", 4.5),
                new Segment("第3天", "10:30 - 22:00", "上班", 11.5),
        };
        System.out.println("==================== 晚班排班表（3天一轮） ====================");
        System.out.printf("%-8s %-20s %-6s %-10s%n", "天数", "时间段", "状态", "时长(h)");
        System.out.println("------------------------------------------------------------");
        double workTotal = 0, restTotal = 0;
        for (Segment s : schedule) {
            System.out.printf("%-8s %-20s %-6s %-10.1f%n", s.day(), s.period(), s.status(), s.hours());
            if ("上班".equals(s.status())) {
                workTotal += s.hours();
            } else {
                restTotal += s.hours();
            }
        }
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "上班", workTotal);
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "休息", restTotal);
        double total = workTotal + restTotal;
        System.out.printf("上班:休息 ≈ %.2f : 1（上班占 %.1f%%，休息占 %.1f%%）%n",
                workTotal / restTotal, workTotal / total * 100, restTotal / total * 100);

        // 宏观排班：上 onDays 天班（含上面的班内工作/休息） + 休 offDays 天（完全休息）
        int onDays = 3, offDays = 3;
        double cycleWork = workTotal;                // 上班 days 内的总工作
        double cycleDays = onDays + offDays;
        System.out.printf("总工作 ≈ %.1f h（日均 %.2f h）%n",
                cycleWork / cycleDays * 7, cycleWork / cycleDays * 7 / 5);
        System.out.println("============================================================");
    }

    @Test
    public void sleepDate() {
//      第一天下班时间
        LocalDate sleep = LocalDate.of(2026, 10, 15);
        for (int i = 0; i < 10; i++) {
            sleep = printSleepDate(sleep);
        }
    }

    private LocalDate printSleepDate(LocalDate sleep) {
        System.out.print(STR."\{sleep}, ");
        System.out.print(STR."\{sleep.plusDays(1)}, ");
        System.out.print(STR."\{sleep.plusDays(2)}, ");
        System.out.print(STR."\{sleep.plusDays(3)}");
        System.out.println();
        return sleep.plusDays(6);
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
}
