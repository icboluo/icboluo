package com.icboluo.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author icboluo
 * @since 2020-08-30 14:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {

    private String name;
    private int age;

    public void eat() {
        System.out.println("eat");
    }
}
