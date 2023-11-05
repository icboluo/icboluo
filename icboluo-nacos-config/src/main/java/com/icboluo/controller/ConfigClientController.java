package com.icboluo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注解 @RefreshScope 可以使应用程序重新加载配置信息，而不需要重新启动应用程序；可以让配置中心的配置信息实时生效
 * @author icboluo
 * @since 2020-08-25 20:43
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
