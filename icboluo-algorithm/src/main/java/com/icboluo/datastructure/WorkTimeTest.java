package com.icboluo.datastructure;

import com.icboluo.util.MathUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @author icboluo
 * @since 2026-07-03 22:23
 */
class WorkTimeTest {
    private static final String[] WEEK_HEADERS = {"一", "二", "三", "四", "五", "六", "日"};

    @Test
    public void workTime() {
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
    public void testCalendar() {
        LocalDate startWorkDate = LocalDate.of(2026, 6, 27);
        int startYear = LocalDate.now().getYear();
        int startMonth = startWorkDate.getMonthValue();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 3; i++) {
            YearMonth yearMonth = YearMonth.of(startYear, startMonth).plusMonths(i);
            printMonthCalendar(yearMonth.getYear(), yearMonth.getMonthValue(), startWorkDate, 3, 3, false, today);
            if (i < 2) {
                System.out.println();
            }
        }
    }

    private void printMonthCalendar(int year, int month, LocalDate startWorkDate, int workDays, int restDays, boolean morningFirst, LocalDate today) {
        int cycleDays = workDays + restDays;
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        // 计算每月第一天是周几（0=周一, 1=周二, ..., 6=周日）
        int firstDayOfWeek = yearMonth.atDay(1).getDayOfWeek().getValue() - 1;

        System.out.println(year + "年" + month + "月日历");
        System.out.println("==========================================");
        System.out.println(String.join("\t\t ", WEEK_HEADERS));
        int lastWorkDay = printMonthDays(yearMonth, daysInMonth, firstDayOfWeek, startWorkDate, cycleDays, workDays, morningFirst, today);
        if (lastWorkDay > 0) {
            printLastWorkDayInfo(year, month, lastWorkDay, cycleDays, morningFirst);
        }
    }

    private int printMonthDays(YearMonth yearMonth, int daysInMonth, int firstDayOfWeek, LocalDate startWorkDate, int cycleDays, int workDays, boolean morningFirst, LocalDate today) {
        int lastWorkDay = 0;
        // 计算起始日期到当月第一天之间的天数，用于确定周期位置
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        long daysToFirstOfMonth = java.time.temporal.ChronoUnit.DAYS.between(startWorkDate, firstDayOfMonth);
        // 判断是否为当前月份，用于标记今天
        boolean isCurrentMonth = today.getYear() == yearMonth.getYear() && today.getMonthValue() == yearMonth.getMonthValue();

        for (int day = 1; day <= daysInMonth; day++) {
            // 计算该日期在周中的列位置，周日放最后
            int dayOfWeek = (firstDayOfWeek + day - 1) % 7;
            // 每月第一天打印空白对齐
            if (day == 1 && firstDayOfWeek > 0) {
                System.out.print("\t\t".repeat(firstDayOfWeek));
            }

            // 计算当前日期相对于起始日期的偏移量
            long daysDiff = daysToFirstOfMonth + (day - 1);
            // 计算在排班周期中的位置
            int positionInCycle = (int) ((daysDiff % cycleDays + cycleDays) % cycleDays);
            // 判断是否为工作日：周期内前workDays天为工作日
            boolean isWorkDay = positionInCycle < workDays;

            String label = getDayLabel(daysDiff, positionInCycle, isWorkDay, cycleDays, morningFirst);
            // 标记今天
            String dayStr = (isCurrentMonth && day == today.getDayOfMonth()) ? day + "*" : String.valueOf(day);
            if (isWorkDay) {
                lastWorkDay = day;
            }

            System.out.print(dayStr + "[" + label + "]\t");
            // 周日换行（因为周日在最后一位）
            if (dayOfWeek == 6) {
                System.out.println();
            }
        }
        // 如果月末不是周日，需要换行
        int lastDayOfWeek = (firstDayOfWeek + daysInMonth - 1) % 7;
        if (lastDayOfWeek != 6) {
            System.out.println();
        }
        return lastWorkDay;
    }

    private String getDayLabel(long daysDiff, int positionInCycle, boolean isWorkDay, int cycleDays, boolean morningFirst) {
        if (!isWorkDay) {
            return "休";
        }
        // 计算当前是第几个完整周期
        int cycleGroup = (int) (daysDiff / cycleDays);
        // 根据班次起始和周期奇偶决定当日班次类型
        String[] shiftsA = morningFirst ? new String[]{"早", "班", "下"} : new String[]{"下", "班", "晚"};
        String[] shiftsB = morningFirst ? new String[]{"下", "班", "晚"} : new String[]{"早", "班", "下"};
        return (cycleGroup % 2 == 0 ? shiftsA : shiftsB)[positionInCycle % 3];
    }

    private void printLastWorkDayInfo(int year, int month, int lastWorkDay, int cycleDays, boolean morningFirst) {
        LocalDate startWorkDate = LocalDate.of(year, month, 1);
        LocalDate lastDate = LocalDate.of(year, month, lastWorkDay);
        long daysDiff = java.time.temporal.ChronoUnit.DAYS.between(startWorkDate, lastDate);
        int positionInCycle = (int) ((daysDiff % cycleDays + cycleDays) % cycleDays);
        int cycleGroup = (int) (daysDiff / cycleDays);
        String[] shiftsA = morningFirst ? new String[]{"早", "班", "下"} : new String[]{"下", "班", "晚"};
        String[] shiftsB = morningFirst ? new String[]{"下", "班", "晚"} : new String[]{"早", "班", "下"};
        String shiftLabel = (cycleGroup % 2 == 0 ? shiftsA : shiftsB)[positionInCycle % 3];
        String endTime = switch (shiftLabel) {
            case "早" -> "早上上班";
            case "班" -> "全天班";
            case "下" -> "晚上下班";
            case "晚" -> "深夜下班";
            default -> "";
        };
        System.out.println("最后一天上班（" + lastWorkDay + "号）[" + shiftLabel + "]：" + endTime);
    }

    @Test
    public void sleepDate() {
        LocalDate sleep = LocalDate.of(2026, 6, 29);
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
}
