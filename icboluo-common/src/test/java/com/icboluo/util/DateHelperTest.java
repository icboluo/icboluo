package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateHelperTest {

    @Test
    void dd() {
        ThreadLocal<List<LocalDateTime>> userContext = DateHelper.userContext;
        List<LocalDateTime> localDateTimes = userContext.get();
        localDateTimes.add(LocalDateTime.now());
    }
}