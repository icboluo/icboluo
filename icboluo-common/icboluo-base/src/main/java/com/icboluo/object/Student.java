package com.icboluo.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author icboluo
 * @since 2022-04-05 15:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student implements Serializable {

    /**
     * 年龄
     */
    private int age;

    /**
     * 姓名
     */
    private String name;

    private Status status;

    public enum Status {
        /**
         * 自由
         */
        FREE,
        /**
         * 忙碌
         */
        BUSY,
        /**
         * 休假
         */
        VOCATION
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Serial
    private static final long serialVersionUID = 4983659706961705248L;
}
