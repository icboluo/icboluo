package com.icboluo.spring.bean;

import com.icboluo.spring.util.BeanFactory;
import lombok.ToString;

@ToString
public class Student0 {
    private AccountMapper myAccountdao = (AccountMapper) BeanFactory.getBean("accountMapperImpl");
}
