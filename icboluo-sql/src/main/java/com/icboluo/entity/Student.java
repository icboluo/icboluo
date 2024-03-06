package com.icboluo.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author icboluo
 * @since 2024-03-06 23:40
 */
@Data
@Entity
@Table(name = "student")
public class Student {

    /**
     * GenerationType 生成类型 IDENTITY 身份
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;
}
