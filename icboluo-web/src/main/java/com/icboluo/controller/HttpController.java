package com.icboluo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author icboluo
 * @date 2021-15-17 21:15
 */
@RequestMapping("/http")
public class HttpController {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://fundgz.1234567.com.cn/js/001186.js?rt=1463558676006"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
