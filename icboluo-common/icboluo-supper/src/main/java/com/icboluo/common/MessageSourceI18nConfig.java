package com.icboluo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractResourceBasedMessageSource;

/**
 * @author icboluo
 */
@Configuration(value = "supperMessageSourceI18nConfig")
public class MessageSourceI18nConfig {
    @Resource
    private MessageSource messageSource;

    @PostConstruct
    public void addMessageSourceBasenames() {
        if (messageSource instanceof AbstractResourceBasedMessageSource) {
            ((AbstractResourceBasedMessageSource) messageSource).addBasenames("i18n/supper");
        }
    }
}
