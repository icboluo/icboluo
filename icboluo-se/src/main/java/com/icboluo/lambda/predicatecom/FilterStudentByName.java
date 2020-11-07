package com.icboluo.lambda.predicatecom;

import com.icboluo.dataobject.Student;

import java.util.function.Predicate;

public class FilterStudentByName implements Predicate<Student> {
    @Override
    public boolean test(Student s) {
        return "one".equals(s.getName().trim());
    }
}
