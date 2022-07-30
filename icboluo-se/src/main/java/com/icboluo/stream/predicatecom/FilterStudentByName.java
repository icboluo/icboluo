package com.icboluo.stream.predicatecom;


import com.icboluo.object.Student;

import java.util.function.Predicate;

public class FilterStudentByName implements Predicate<Student> {
    @Override
    public boolean test(Student s) {
        return "one".equals(s.getName().trim());
    }
}
