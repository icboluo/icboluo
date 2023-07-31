package com.icboluo.service;

import com.icboluo.entity.Student;
import com.icboluo.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
class StudentServiceTest {
    @Test
    void generateList() {
        StudentService studentService = new StudentServiceImpl();
        List<Student> list = studentService.generateList(10);
        log.info(list.toString());
        Assert.isTrue(list.size() == 10, "");
    }

    @Test
    void generateEnName() {
        StudentService studentService = new StudentServiceImpl();
        String name = studentService.generateEnName();
        log.info("name is " + name);
        Assert.hasText(name, "");
    }
}
