package com.icboluo.file;


import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author icboluo
 * @date 2020-08-12 15:35
 */
@AllArgsConstructor
@ToString
class Student implements Serializable {
    private int age;
    private String name;

    /**
     * 表示该类的序列化版本号永远是，不知道写了有啥用
     */
    private static final long serialVersionUID = 4983659706961705248L;
}
