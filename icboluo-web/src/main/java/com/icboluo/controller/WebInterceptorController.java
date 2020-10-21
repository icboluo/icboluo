package com.icboluo.controller;

import com.icboluo.annotation.WebContextAnno;
import com.icboluo.enumeration.WebContextEnum;
import com.icboluo.interceptor.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        String userCode = UserContext.getUserCode();
        return userCode;

    }
}
