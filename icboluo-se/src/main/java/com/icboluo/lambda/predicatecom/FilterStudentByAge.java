package com.icboluo.lambda.predicatecom;

import com.icboluo.dataobject.Student;

import java.util.function.Predicate;

/**
 * @author lp
 */
public  class FilterStudentByAge implements Predicate<Student> {
    @Override
    public boolean test(Student s) {
        return s.getAge()>55;
    }
}
