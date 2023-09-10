package com.icboluo.e_login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icboluo.constant.HttpConstant;
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
    public Integer queryByUsernameAndPassword(String username, String password) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HttpConstant.USER_SERVICE + "/user/login?username=" + username + "&password=" + password))
                .build();
        String body = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
                .body();
        JSONObject jsonObject = JSON.parseObject(body);
        return (Integer) jsonObject.get("code");
    }
}
