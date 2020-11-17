package com.icboluo.controller;

import com.icboluo.annotation.RequestBodyParam;
import com.icboluo.annotation.UserCode;
import com.icboluo.annotation.WebContextAnno;
import com.icboluo.enumeration.WebContextEnum;
import com.icboluo.interceptor.UserContext;
import com.icboluo.util.StudentUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @date 2020-09-17 23:22
 */
@RestController
@RequestMapping("webInterceptor")
@WebContextAnno(service = WebContextEnum.WEB)
public class WebInterceptorController {

    @GetMapping("/getUserCode")
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
}
