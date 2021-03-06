package com.icboluo.controller;

import com.icboluo.annotation.RecordTime;
import com.icboluo.annotation.RequestBodyParam;
import com.icboluo.annotation.UserCode;
import com.icboluo.annotation.WebContextAnno;
import com.icboluo.common.ResponseResult;
import com.icboluo.common.enumeration.WebContextEnum;
import com.icboluo.dataobject.OrderCO;
import com.icboluo.interceptor.UserContext;
import com.icboluo.util.StudentUtil;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @date 2020-09-17 23:22
 */
@RestController
@RequestMapping("webInterceptor")
@ResponseResult
//@WebContextAnno(service = WebContextEnum.WEB)
public class WebInterceptorController {

    @GetMapping("/getUserCode")
    @WebContextAnno(service = WebContextEnum.WEB)
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
    public String studentTest() {
        StudentUtil.print();
        return "ok";
    }

    @GetMapping("/validate")
    public Response validate(@Validated @RequestBody OrderCO co) {
        System.out.println(co);
        return R.correct(co);
    }
}
