package com.icboluo.e_login;

import com.icboluo.constant.HttpConstant;
import com.icboluo.util.response.SingleResponse;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author icboluo
 */
public class LoginMapper {

    @SneakyThrows
    public SingleResponse queryByUsernameAndPassword(String username, String password) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HttpConstant.USER_SERVICE + "/user/login?id=" + username + "&pwd=" + password))
                .build();
        String body = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
                .body();
        return SingleResponse.generate(body);
    }

    @SneakyThrows
    public SingleResponse register(String username, String password) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HttpConstant.USER_SERVICE + "/user/register?id=" + username + "&pwd=" + password))
                .build();
        String body = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
                .body();
        return SingleResponse.generate(body);
    }
}
