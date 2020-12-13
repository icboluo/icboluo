package com.icboluo.file;


import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = 4983659706961705248L;
}
