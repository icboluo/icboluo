package com.icboluo.stream.predicatecom;


import com.icboluo.object.StatusStudent;

import java.util.function.Predicate;

/**
 * @author lp
 */
public  class FilterStudentByAge implements Predicate<StatusStudent> {
    @Override
    public boolean test(StatusStudent s) {
        return s.getAge()>55;
    }
}
