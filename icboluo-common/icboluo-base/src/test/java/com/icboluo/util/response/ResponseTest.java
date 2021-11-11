package com.icboluo.util.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ResponseTest {

    @Test
    void page() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        Response page = R.correct(list, list, list, list).page();
        System.out.println("page = " + page);
    }
}
