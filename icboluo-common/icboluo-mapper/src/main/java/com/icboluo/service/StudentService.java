package com.icboluo.service;

import com.icboluo.entity.Student;

import java.util.List;

/**
 * @author icboluo
 * @since 2023-07-31 22:36
 */
public interface StudentService {

    List<Student> generateList(int size);

    String generateEnName()  ;

    String generateZhName()  ;
}
