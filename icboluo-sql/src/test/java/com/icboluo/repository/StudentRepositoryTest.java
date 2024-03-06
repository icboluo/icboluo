package com.icboluo.repository;

import com.icboluo.SqlApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SqlApplication.class)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void test1() {
        System.out.println(studentRepository.findAll());
    }
}
