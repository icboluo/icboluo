package com.icboluo.controller;

import com.icboluo.annotation.*;
import com.icboluo.dataobject.OrderCO;
import com.icboluo.enumerate.ServiceNameEnum;
import com.icboluo.interceptor.UserContext;
import com.icboluo.util.StaticTestUtil;
import com.icboluo.util.ValidateUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2020-09-17 23:22
 */
@RestController
@RequestMapping("webInterceptor")
@ResponseResult
//@WebContextAnno(service = WebContextEnum.WEB)
public class WebInterceptorController {

    @GetMapping("/getUserCode")
    @WebContextAnno(service = ServiceNameEnum.WEB)
    @RecordTime
    public String getUserCode() {
        return UserContext.userCode();
    }

    @GetMapping("/userCodeAnnotation")
    public String userCodeAnnotation(@UserCode String userCode) {
        return userCode;
    }

    @GetMapping("/bodyParam")
    public String bodyParam(@RequestBodyParam(required = false) String id, @RequestBodyParam(required = false) String name,
                            @RequestBodyParam(required = false) String code
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
