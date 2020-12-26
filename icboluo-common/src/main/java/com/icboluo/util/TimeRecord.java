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
 * @date 2020/12/17 21:45
 */
@Slf4j
public class TimeRecord {

    /**
     * 记录是第几次操作
     */
    private static Integer operationTime;

    /**
     * 记录每次的操作时间
     */
    private static List<Long> times;

    /**
     * 记录第key个节点到第key+1个节点执行的功能，用于打印日志
     */
    private static Map<Integer, String> recordMsgMap;

    public static void start() {
        start(null);
    }

    public static void start(String msg) {
        log.warn("计时开始");
        operationTime = 0;
        recordMsgMap = new HashMap<>();
        if (!StringUtils.isEmpty(msg)) {
            recordMsgMap.put(operationTime, msg);
        }
        times = new ArrayList<>();
        times.add(System.currentTimeMillis());
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
        times.add(now);
        String preMsg = recordMsgMap.get(operationTime);
        if (StringUtils.isEmpty(preMsg)) {
            log.warn("第{}个节点到下一个节点共消耗 {} 毫秒时间", operationTime, now - preTime);
        } else {
            log.warn("功能：{} 消耗的时间为 {} 毫秒", preMsg, now - preTime);
        }
        operationTime++;
        if (!StringUtils.isEmpty(msg)) {
            recordMsgMap.put(operationTime, msg);
        }
    }

    public static void build() {
        record();
        Long allTime = times.get(times.size() - 1) - times.get(0);
        log.warn("整个程序耗时 {} 毫秒", allTime);
        Long preTime = times.get(0);
        Long time;
        long maxTime = 0L;
        int maxAfterIndex = 0;
        for (int i = 0; i < times.size(); i++) {
            time = times.get(i);
            if (time - preTime > maxTime) {
                maxAfterIndex = i;
                maxTime = time - preTime;
            }
            preTime = time;
        }
        String rate = MathHelper.dividePercentage(maxTime, allTime);
        String msg = recordMsgMap.get(maxAfterIndex - 1);
        log.warn("第{}个节点到下一个节点消耗时间最久为 {} 毫秒，功能是：{}，占总时间 {}", maxAfterIndex - 1, msg, maxTime, rate);
    }
}
