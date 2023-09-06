package com.icboluo.controller;

import com.icboluo.annotation.AuthAnno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author icboluo
 * @since 2021-33-13 23:33
 */
@RestController
@RequestMapping(value = "rest")
public class ResultController {

    @GetMapping("uuid")
    public String uuid() {
        return UUID.randomUUID().toString();
    }

    @GetMapping("integer")
    public Integer integer() {
        return 11;
    }

    @GetMapping("map")
    public Map<String, String> map() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        map.put("e", "f");
        map.put("g", "h");
        return map;
    }

    @GetMapping("auth")
    @AuthAnno(role = "1")
    public Integer auth() {
        return 11;
    }
}
