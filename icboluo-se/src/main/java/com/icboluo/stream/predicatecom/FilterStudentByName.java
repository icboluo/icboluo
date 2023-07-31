package com.icboluo.stream.predicatecom;


import com.icboluo.object.StatusStudent;

import java.util.function.Predicate;

public class FilterStudentByName implements Predicate<StatusStudent> {
    @Override
    public boolean test(StatusStudent s) {
        return "one".equals(s.getName().trim());
    }
}
