package com.icboluo.controller;

import com.icboluo.annotation.*;
import com.icboluo.dataobject.OrderCO;
import com.icboluo.enumerate.ServiceNameEnum;
import com.icboluo.interceptor.WebContext;
import com.icboluo.util.StaticTestUtil;
import com.icboluo.util.ValidateUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2020-09-17 23:22
 */
@RestController
@RequestMapping("webInterceptor")
//@WebContextAnno(service = WebContextEnum.WEB)
public class WebInterceptorController {

    @GetMapping("/getUserCode")
    @WebContextAnno(service = ServiceNameEnum.WEB)
    public String getUserCode() {
        return WebContext.userCode();
    }

    @GetMapping("/userCodeAnnotation")
    public String userCodeAnnotation(@UserCode String userCode) {
        return userCode;
    }

    @GetMapping("/bodyParam")
    public String bodyParam(@RequestParam(required = false) String id, @RequestParam(required = false) String name,
                            @RequestParam(required = false) String code
    ) {
        return id + name + code;
    }

    @GetMapping("/localDateTime")
    public LocalDateTime localDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        return now;
    }

    @GetMapping("/studentTest")
    public void studentTest() {
        StaticTestUtil.print();
    }

    @GetMapping("/validate")
    public void validate(@Validated @RequestBody OrderCO client) {
        ValidateUtil.validateProperty(client, "code");
        System.out.println(client);
    }
}
