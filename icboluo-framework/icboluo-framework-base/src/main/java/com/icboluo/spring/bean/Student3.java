package com.icboluo.spring.bean;

import lombok.ToString;

/**
 * @author icboluo
 * @since 2022-05-29 15:27
 */
@ToString
public class Student3 {

    private Integer age;

    private String name;

    private AccountMapper accountMapper;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
}
