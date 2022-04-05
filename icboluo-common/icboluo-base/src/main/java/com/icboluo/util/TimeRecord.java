package com.icboluo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p/>功能：打印方法的执行时间
 * <p/>注释使用的是功能执行前
 *
 * @author icboluo
 * @since 2020/12/17 21:45
 */
@Slf4j
public class TimeRecord {

    /**
     * 记录每次的操作时间
     */
    private static List<Long> times;
    /**
     * 每次操作消耗内存
     */
    private static List<Long> rams;
    /**
     * 用于记录日志
     * 第3个节点记录第3个日志
     */
    private static Map<Integer, String> recordMsgMap;

    private static Runtime runtime;

    public static void start() {
        start(null);
    }

    public static void start(String msg) {
        log.warn("计时开始");
        times = new ArrayList<>();
        rams = new ArrayList<>();
        runtime = Runtime.getRuntime();
        times.add(System.currentTimeMillis());
        rams.add(runtime.maxMemory() - runtime.freeMemory());
        recordMsgMap = new HashMap<>();
        if (StringUtils.hasText(msg)) {
            recordMsgMap.put(0, msg);
        }
    }

    /**
     * 记录当前的操作时间和操作次数
     */
    public static void record() {
        record(null);
    }

    public static void record(String msg) {
        long now = System.currentTimeMillis();
        long preTime = times.get(times.size() - 1);
        String preMsg = recordMsgMap.get(times.size() - 1);
        if (StringUtils.hasText(preMsg)) {
            log.warn("功能：{} 消耗的时间为 {} 毫秒", preMsg, now - preTime);
        } else {
            log.warn("第{}个节点到下一个节点共消耗 {} 毫秒时间", times.size() - 1, now - preTime);
        }
        times.add(now);
        rams.add(runtime.maxMemory() - runtime.freeMemory());
        if (StringUtils.hasText(msg)) {
            recordMsgMap.put(times.size()-1, msg);
        }
    }

    public static void build() {
        record();
        Long allTime = times.get(times.size() - 1) - times.get(0);
        log.warn("整个程序耗时 {} 毫秒", allTime);

        Long preTime = times.get(0);
        Long curTime;
        long maxTime = 0L;
        int maxAfterIndex = 0;
        for (int i = 1; i < times.size(); i++) {
            curTime = times.get(i);
            if (curTime - preTime > maxTime) {
                maxAfterIndex = i;
                maxTime = curTime - preTime;
            }
            preTime = curTime;
        }
        String rate = MathHelper.dividePercentage(maxTime, allTime);
        String msg = recordMsgMap.get(maxAfterIndex - 1);
        log.warn("第{}个节点到下一个节点消耗时间最久为 {} 毫秒，功能是：{}，占总时间 {}", maxAfterIndex - 1, maxTime, msg, rate);
    }
}
