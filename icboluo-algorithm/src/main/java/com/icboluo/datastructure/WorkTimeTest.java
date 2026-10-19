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
        float contribute = 10F;
        float avg = 9F;
        float target = 8.5F;
        int totalDays = 20;

        BigDecimal workDay = MathUtil.divide(contribute, avg - 8, 1);
        System.out.println(STR."已经上班的天数 = \{workDay}");

        // 加班天数(a) / 不加班天数(b) 由“总天数 + 日均工时”推导（无需手填）：
        // 约定 加班日=10h、不加班日=7h、基线=8h；a+b=总天数，10a+7b=avg*总天数
        // 解得 a = 总天数*(avg-7)/3，b = 总天数 - a
        float overtimeDays = totalDays * (avg - 7F) / 3F;   // a：原始加班天数（含小数）

        // 第一组：加班天数向上取整（剩余不加班天数相应向下凑整）
        int overtimeUp = (int) Math.ceil(overtimeDays);
        int normalUp = totalDays - overtimeUp;
        System.out.println(STR."（向上取整）剩余加班天数 a = \{overtimeUp}，剩余不加班天数 b = \{normalUp}");
        float excessUp = overtimeUp * (10F - target) - normalUp * (target - 7F);
        System.out.println(STR."本月多余的贡献工时（相对目标，向上取整） = \{excessUp}");

        // 第二组：加班天数向下取整（剩余不加班天数相应向上凑整）
        int overtimeDown = (int) Math.floor(overtimeDays);
        int normalDown = totalDays - overtimeDown;
        System.out.println(STR."（向下取整）剩余加班天数 a = \{overtimeDown}，剩余不加班天数 b = \{normalDown}");
        float excessDown = overtimeDown * (10F - target) - normalDown * (target - 7F);
        System.out.println(STR."本月多余的贡献工时（相对目标，向下取整） = \{excessDown}");
    }

    @Test
    public void nightShift() {
        // 晚班排班表（3 天一轮：每天由若干“上班/休息”时段组成）
        // meal：该时段内的吃饭时间（小时），默认 0，第二行夜班含 0.5h 吃饭
        record Segment(String day, String period, String status, double hours, double meal) {
        }
        Segment[] schedule = {
                new Segment("第1天", "07:00 - 15:00", "休息", 8.0, 0.0),
                new Segment("第1天", "15:00 - 01:30", "上班", 10.5, 0.5),
                new Segment("第2天", "01:30 - 07:00", "休息", 5.5, 0.0),
                new Segment("第2天", "07:00 - 16:30", "上班", 9.5, 1),
                new Segment("第2天", "16:30 - 22:00", "休息", 5.5, 0.0),
                new Segment("第2天", "22:00 - 06:00", "上班", 8.0, 0.5),
                new Segment("第3天", "06:00 - 10:30", "休息", 4.5, 0.0),
                new Segment("第3天", "10:30 - 22:00", "上班", 11.5, 1),
        };
        System.out.println("==================== 晚班排班表（3天一轮） ====================");
        System.out.printf("%-8s %-20s %-6s %-10s %-10s%n", "天数", "时间段", "状态", "时长(h)", "吃饭(h)");
        System.out.println("------------------------------------------------------------");
        double workTotal = 0, restTotal = 0, mealTotal = 0;
        for (Segment s : schedule) {
            System.out.printf("%-8s %-20s %-6s %-10.1f %-10.1f%n", s.day(), s.period(), s.status(), s.hours(), s.meal());
            if ("上班".equals(s.status())) {
                workTotal += s.hours();
                mealTotal += s.meal();
            } else {
                restTotal += s.hours();
            }
        }
        double workNet = workTotal - mealTotal;   // 吃饭时间不计入上班，单独扣除
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "上班(净)", workNet);
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "吃饭", mealTotal);
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "休息", restTotal);
        double total = workNet + restTotal + mealTotal;
        System.out.printf("上班(净):休息 ≈ %.2f : 1（上班占 %.1f%%，休息占 %.1f%%，吃饭占 %.1f%%）%n",
                workNet / restTotal, workNet / total * 100, restTotal / total * 100, mealTotal / total * 100);

        // 宏观排班：上 onDays 天班（含上面的班内工作/休息） + 休 offDays 天（完全休息）
        int onDays = 3, offDays = 3;
        double cycleWork = workNet;                  // 上班 days 内的总工作（净，已扣除吃饭）
        double cycleDays = onDays + offDays;
        System.out.printf("总工作 ≈ %.1f h（日均 %.2f h）%n",
                cycleWork / cycleDays * 7, cycleWork / cycleDays * 7 / 5);
        System.out.println("============================================================");
    }

    @Test
    public void dayShift() {
        // 早班排班表（3 天一轮：每天由若干“上班/休息”时段组成）
        record Segment(String day, String period, String status, double hours, double meal) {
        }
        Segment[] schedule = {
                new Segment("第1天", "07:00 - 18:00", "上班", 11.0, 1),
                new Segment("第1天", "18:00 - 24:00", "休息", 6.0, 0.0),
                new Segment("第1天", "24:00 - 07:00", "上班", 7.0, 0.5),
                new Segment("第2天", "07:00 - 12:30", "休息", 5.5, 0.0),
                new Segment("第2天", "12:30 - 23:00", "上班", 10.5, 1),
                new Segment("第2天", "23:00 - 06:00", "休息", 7.0, 0.0),
                new Segment("第3天", "06:00 - 13:00", "上班", 7.0, 1),
        };
        System.out.println("==================== 早班排班表（3天一轮） ====================");
        System.out.printf("%-8s %-20s %-6s %-10s %-10s%n", "天数", "时间段", "状态", "时长(h)", "吃饭(h)");
        System.out.println("------------------------------------------------------------");
        double workTotal = 0, restTotal = 0, mealTotal = 0;
        for (Segment s : schedule) {
            System.out.printf("%-8s %-20s %-6s %-10.1f %-10.1f%n", s.day(), s.period(), s.status(), s.hours(), s.meal());
            if ("上班".equals(s.status())) {
                workTotal += s.hours();
                mealTotal += s.meal();
            } else {
                restTotal += s.hours();
            }
        }
        double workNet = workTotal - mealTotal;   // 吃饭时间不计入上班，单独扣除
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "上班(净)", workNet);
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "吃饭", mealTotal);
        System.out.printf("%-8s %-20s %-6s %-10.1f%n", "合计", "3天一轮", "休息", restTotal);
        double total = workNet + restTotal + mealTotal;
        System.out.printf("上班(净):休息 ≈ %.2f : 1（上班占 %.1f%%，休息占 %.1f%%，吃饭占 %.1f%%）%n",
                workNet / restTotal, workNet / total * 100, restTotal / total * 100, mealTotal / total * 100);

        // 宏观排班：上 onDays 天班（含上面的班内工作/休息） + 休 offDays 天（完全休息）
        int onDays = 3, offDays = 3;
        double cycleWork = workNet;                  // 上班 days 内的总工作（净，已扣除吃饭）
        double cycleDays = onDays + offDays;
        System.out.printf("总工作 ≈ %.1f h（日均 %.2f h）%n",
                cycleWork / cycleDays * 7, cycleWork / cycleDays * 7 / 5);
        System.out.println("============================================================");
    }

    @Test
    public void workDate() {
        String startType = "早班";
        String alt = "早班".equals(startType) ? "晚班" : "早班";
        LocalDate sleep = LocalDate.of(2026, 10, 20);
        for (int i = 0; i < 10; i++) {
            String type = i % 2 == 0 ? startType : alt;   // 第一个按用户输入，剩下轮询
            sleep = printWorkDate(sleep, type);
        }
    }

    private LocalDate printWorkDate(LocalDate sleep, String type) {
        System.out.print(STR."\{type}: ");
        System.out.print(STR."\{sleep}, ");
        System.out.print(STR."\{sleep.plusDays(1)}, ");
        System.out.print(STR."\{sleep.plusDays(2)}, ");
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
