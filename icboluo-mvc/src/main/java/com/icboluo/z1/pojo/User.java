package com.icboluo.z1.pojo;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;

    private String name;
    private Integer age;
    private Boolean isMarry;
    private Float income;
    private String[] interests;
}
