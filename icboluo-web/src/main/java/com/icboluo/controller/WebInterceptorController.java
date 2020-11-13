package com.icboluo.controller;

import com.icboluo.annotation.RequestBodyParam;
import com.icboluo.annotation.UserCode;
import com.icboluo.annotation.WebContextAnno;
import com.icboluo.enumeration.WebContextEnum;
import com.icboluo.interceptor.UserContext;
import com.icboluo.util.StudentUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String bodyParam(@RequestBodyParam(required = false) String userCode, @RequestBodyParam(required = false) String id,
                            @RequestParam(required = false) String abc
    ) {
        return userCode;
    }

    @GetMapping("/studentTest")
    public String studentTest() {
        StudentUtil.print();
        return "ok";
    }
}
