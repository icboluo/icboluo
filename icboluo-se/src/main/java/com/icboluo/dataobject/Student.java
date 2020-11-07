package com.icboluo.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    /**
     * 年龄
     */
    private int age;
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
}
