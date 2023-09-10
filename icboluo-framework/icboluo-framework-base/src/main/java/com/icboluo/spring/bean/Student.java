package com.icboluo.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @since 2022-05-29 15:27
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private Integer age;

    private String name;

    private AccountMapper accountMapper;
}
