package com.icboluo.controller;

import com.icboluo.util.redis.RedisList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @date 2021-46-02 12:46
 */
@RestController
@RequestMapping("/listRedis")
public class ListRedisController {

    @Resource
    private RedisList<Integer> redisList;

    @GetMapping("/add")
    public Boolean add() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(7);
        list.add(10);
        Boolean add = redisList.add("list:one", 1);
        return redisList.addAll("list:integer", list);
    }

    @GetMapping("/update")
    public Boolean update() {
        return redisList.set("list:integer", 0, 77);
    }

    @GetMapping("/remove")
    public Long remove() {
        return redisList.remove("list:integer", 77, 5);
    }

    @GetMapping("/size")
    public void size() {
        List<Integer> all = redisList.get("list:integer");
        System.out.println("all = " + all);
        Integer four = redisList.get("list:integer", 3);
        System.out.println("four = " + four);
        Long size = redisList.size("list:integer");
        System.out.println("size = " + size);
    }
}
