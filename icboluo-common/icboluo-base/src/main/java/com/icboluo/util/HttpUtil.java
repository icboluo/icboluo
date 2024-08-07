package com.icboluo.util;

import com.alibaba.fastjson2.JSON;
import com.icboluo.constant.HttpConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * @author icboluo
 * @since 2020/10/27 22:56
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class HttpUtil {

    /**
     * 发送get请求
     *
     * @param url        http url
     * @param paramName  param name
     * @param paramValue param value
     */
    public static String sendGet(String url, String paramName, String paramValue) {
        url = url + "/?" + paramName + "=" + paramValue;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        return HttpClient.newHttpClient().sendAsync(
                        httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }

    public static String sendPost(String url, Map<String, Object> map) {
        return sendPost(url, JSON.toJSONString(map));
    }

    public static String sendPost(String url, Object body) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
//                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
        return HttpClient.newHttpClient().sendAsync(
                        httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }

    public static void nacosYml() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HttpConstant.NACOS_SERVICE + "/config/yml"))
                .build();
        try {
            String body = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString())
                    .body();
            if ("simple".equals(body) || "test".equals(body)) {
                System.setProperty("spring.profiles.active", body);
            }
        } catch (IOException e) {
            // use local application.yml configuration
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
