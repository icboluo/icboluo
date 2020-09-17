package com.icboluo;

import com.icboluo.common.ReEnum;
import com.icboluo.interceptor.UserContext;
import com.icboluo.util.R;
import com.icboluo.util.Response;
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
