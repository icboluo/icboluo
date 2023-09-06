package com.icboluo.controller;

import com.icboluo.annotation.*;
import com.icboluo.dataobject.OrderCO;
import com.icboluo.enumerate.ServiceNameEnum;
import com.icboluo.interceptor.WebContext;
import com.icboluo.util.StaticTestUtil;
import com.icboluo.util.ValidateUtil;
import jakarta.validation.ConstraintViolation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

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
        Set<ConstraintViolation<OrderCO>> msg = ValidateUtil.validateProperty(client, "code");
        System.out.println(ValidateUtil.buildMsg(msg));
    }
}
