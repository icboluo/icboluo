package com.icboluo.producer;

import com.icboluo.util.I18nException;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author icboluo
 * @since 2020-08-27 19:26
 */
@RestController
@RequestMapping("user")
@Slf4j
@Validated
public class UserProducer {

    private final Map<String, String> account = new HashMap<>();

    @GetMapping("getUserNameById")
    public String getUserNameById(@RequestParam Integer id) {
        log.info("GET请求，进入icboluo-user服务了");
        Objects.requireNonNull(id, "id不能为空");
        if (id <= 10) {
            return "user: " + id;
        } else {
            return null;
        }
    }

    @PostMapping("postUserNameById")
    public String postUserNameById(@RequestBody Integer id) {
        log.info("POST请求，进入icboluo-user服务了");
        Objects.requireNonNull(id, "id不能为空");
        if (id <= 10) {
            return "user: " + id;
        } else {
            return null;
        }
    }

    @Validated
    @GetMapping("register")
    public void register(@Validated @NotEmpty String id, String pwd) {
        account.put(id, pwd);
    }

    @GetMapping("login")
    public void login(String id, String pwd) {
        if (!account.containsKey(id) || !Objects.equals(account.get(id), pwd)) {
            throw new I18nException("account or password error");
        }
    }

    @PostMapping("login")
    public void login(@RequestBody Map<String,String> map) {
        String id = map.get("id");
        String pwd = map.get("password");
        if (!account.containsKey(id) || !Objects.equals(account.get(id), pwd)) {
            throw new I18nException("account or password error");
        }
    }
}
