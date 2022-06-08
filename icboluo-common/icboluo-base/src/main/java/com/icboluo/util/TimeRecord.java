package com.icboluo.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>功能：打印方法的执行时间
 * <p/>注释使用的是功能执行前
 *
 * @author icboluo
 * @since 2020/12/17 21:45
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeRecord {

    private static List<Time> timeList;

    public static void start() {
        start(null);
    }

    public static void start(String msg) {
        log.warn("计时开始");
        timeList = new ArrayList<>();
        timeList.add(new Time(System.currentTimeMillis(), msg));
    }

    /**
     * 记录当前的操作时间和操作次数
     */
    public static void recordMsg() {
        recordMsg(null);
    }

    public static void recordMsg(String msg) {
        long now = System.currentTimeMillis();
        Time time = timeList.get(timeList.size() - 1);
        long preTime = time.curTime;
        String preMsg = time.msg;

        if (preMsg != null && !"".equals(preMsg)) {
            log.warn("功能：{} 消耗的时间为 {} 毫秒", preMsg, now - preTime);
        } else {
            log.warn("第{}个节点到下一个节点共消耗 {} 毫秒时间", timeList.size() - 1, now - preTime);
        }
        timeList.add(new Time(now, msg));
    }

    public static void build() {
        recordMsg();
        Long allTime = timeList.get(timeList.size() - 1).curTime - timeList.get(0).curTime;
        log.warn("整个程序耗时 {} 毫秒", allTime);

        Long preTime = timeList.get(0).curTime;
        long maxTime = 0L;
        int maxAfterIndex = 0;
        for (int i = 1; i < timeList.size(); i++) {
            Long curTime = timeList.get(i).curTime;
            if (curTime - preTime > maxTime) {
                maxAfterIndex = i;
                maxTime = curTime - preTime;
            }
            preTime = curTime;
        }
        String rate = MathUtil.dividePercentage(maxTime, allTime);
        String msg = timeList.get(maxAfterIndex - 1).msg;
        if (msg != null && !"".equals(msg)) {
            log.warn("第{}个节点到下一个节点消耗时间最久为 {} 毫秒，功能是：{}，占总时间 {}", maxAfterIndex - 1, maxTime, msg, rate);
        } else {
            log.warn("第{}个节点到下一个节点消耗时间最久为 {} 毫秒，占总时间 {}", maxAfterIndex - 1, maxTime, rate);
        }
    }

    @AllArgsConstructor
    static class Time {

        Long curTime;
        String msg;
    }
}
