package com.icboluo.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lp
 */
@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public String test(String s) {
        log.info(s);
        while (true) {
            int i = 10;
            i++;
            if (i == 0) {
                break;
            }
        }
        return s;
    }

    @GetMapping("/ddd")
    public void test2(@RequestParam Integer id,
                      @RequestParam String map) {
        Map map1 = JSON.parseObject(map, Map.class);
        System.out.println(id);
        System.out.println(map);
    }
}
