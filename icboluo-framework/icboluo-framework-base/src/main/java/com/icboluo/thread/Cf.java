package com.icboluo.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author icboluo
 * @since 2025-07-10 21:54
 */
@Slf4j
public class Cf {

    public static void main(String[] args) {
        Cf cla = new Cf();
        List<CompletableFuture<String>> li = new ArrayList<>();
//        将cfList join
        CompletableFuture.allOf(li.toArray(new CompletableFuture<?>[0])).join();
//        不会产生异常日志，需要加上 exceptionally
        CompletableFuture.runAsync(() -> cla.runTask(""));
        cla.cf(1);
    }


    public void runTask(String operationId) {
    }

    public void cf(@RequestParam int a) {
        switch (a) {
            case 1 -> CompletableFuture.runAsync(this::ex);
            case 2 -> CompletableFuture.runAsync(this::ex).join();
            case 3 -> CompletableFuture.runAsync(this::ex).exceptionally(e -> {
                log.error("fail: ", e);
                return null;
            });
            case 4 -> CompletableFuture.runAsync(this::ex).exceptionally(e -> {
                return null;
            });
        }
    }

    public void ex() {
        throw new RuntimeException();
    }
}
