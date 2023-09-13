package com.icboluo.z3_jedis.bean;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String student_name;
    private String student_no;
    private Integer sex;
    private Integer class_id;
    private Integer subject_no;
}
