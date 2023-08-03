package com.icboluo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author icboluo
 * @since 2022-05-12 20:57
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleThreadUtil {

    public static void sleep(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep5s() {
        try {
            log.warn("start sleep 5 second");
            Thread.sleep(5000);
            log.warn("have already sleep 5 second");
        } catch (InterruptedException e) {
            log.error("one error occur,Please check", e);
            // sonarLint 推荐写法
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 无限的循环
     */
    @SneakyThrows
    public static void infiniteLoop() {
        int i = 0;
        while (i < 10000) {
            log.warn(i + " start sleep 3 second");
            Thread.sleep(3000);
            log.warn(i + " have already sleep 3 second");
            i++;
        }
    }
}
