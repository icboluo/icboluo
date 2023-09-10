package com.icboluo.z2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String sex;
    private int age;
    private String address;
    private String qq;
    private String email;
}
