package com.icboluo.framework;

import lombok.SneakyThrows;

import java.util.concurrent.Executors;

public class VirtualThreadDemo {
    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread.ofVirtual().start(() -> print(finalI));
        }
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                executor.submit(() -> print(finalI));
            }
        }
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.submit(() -> print(finalI));
        }

        System.out.println(System.currentTimeMillis());
        Thread.sleep(200L);
        System.out.println("ok");

        send("https://127.0.0.1", "id=123");
    }

    @SneakyThrows
    public static void print(int i) {
        Thread.sleep(1000L);
        System.out.println(i);
    }

    private static void sendClient(String url, String param) {
        try {
            send(url, param);
        } catch (NullPointerException ex) {
            url = url.replace("https", "http");
            send(url, param);
        }
    }

    private static void send(String url, String param) {
        if (!url.startsWith("https")) {
            throw new NullPointerException();
        }
        System.out.printf("send interface,url is [{}], param is [{}]", url, param);
    }
}
