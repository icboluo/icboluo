package com.icboluo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author icboluo
 * @date 2021-33-13 23:33
 */
@RestController
@RequestMapping("/rest")
@ResponseResult
public class ResultController {

    @GetMapping("/uuid")
    public String uuid() {
        return UUID.randomUUID().toString();
    }
}
