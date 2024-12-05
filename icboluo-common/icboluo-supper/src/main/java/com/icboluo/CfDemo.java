package com.icboluo;

import com.icboluo.util.I18nException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author icboluo
 * @since 2024-12-05 22:47
 */
@Slf4j
public class CfDemo {

    public static void main(String[] args) {
        var cla = new CfDemo();
        CompletableFuture<String> haveRet = cla.havaRetFun();
        CompletableFuture<Void> voidRet = cla.voidRetFun();
        cla.asyncJoin(haveRet, voidRet);
        String join = haveRet.join();
        System.out.println("join = " + join);
    }

    private void asyncJoin(CompletableFuture<?>... taskArr) {
        CompletableFuture.allOf(taskArr).handle((result, throwable) -> {
            if (throwable == null) {
                return null;
            }
            if (throwable.getCause() instanceof I18nException) {
                throw new I18nException(((I18nException) throwable.getCause()).getStatus(),
                        throwable.getCause().getMessage());
            } else {
                log.error("", throwable);
                throw new I18nException();
            }
        }).join();
    }

    private CompletableFuture<String> havaRetFun() {
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(
                () -> voidFun());
        CompletableFuture<String> cfTime = CompletableFuture.supplyAsync(
                () -> stringRet());
        return cfTime.thenCombine(CompletableFuture.allOf(cf1), (res, ignore) -> res);
    }

    private CompletableFuture<Void> voidRetFun() {
        return null;
    }

    private String stringRet() {
        return null;
    }

    private void voidFun() {

    }
}
