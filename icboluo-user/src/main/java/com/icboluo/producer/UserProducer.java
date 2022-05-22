package com.icboluo.producer;

import com.icboluo.annotation.ResponseResult;
import com.icboluo.util.IcBoLuoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author icboluo
 * @since 2020-08-27 19:26
 */
@RestController
@RequestMapping("/user")
@ResponseResult
@Slf4j
@Validated
public class UserProducer {

    private final Map<String, String> account = new HashMap<>();

    @GetMapping("/getUserNameById")
    public String getUserNameById(@RequestParam Integer id) {
        log.info("GET请求，进入icboluo-user服务了");
        Objects.requireNonNull(id, "id不能为空");
        if (id <= 10) {
            return "user: " + id;
        } else {
            return null;
        }
    }

    @PostMapping("/postUserNameById")
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
    @GetMapping("/register")
    public void register(@Validated @NotEmpty String id, String pwd) {
        account.put(id, pwd);
    }

    @GetMapping("/login")
    public void login(String id, String pwd) {
        if (!account.containsKey(id) || !Objects.equals(account.get(id), pwd)) {
            throw new IcBoLuoException("account or password error");
        }
    }
}
