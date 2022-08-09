package com.spring;

import lombok.Data;

/**
 * @author icboluo
 * @since 2022-08-10 0:05
 */
@Data
public class BeanDefinition {

    private Class<?> clazz;

    private String scope;
}
