package com.icboluo.util;

import icboluo.util.DateHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class DateHelperTest {

    @Test
    void dd() {
        ThreadLocal<List<LocalDateTime>> userContext = DateHelper.USER_CONTEXT;
        List<LocalDateTime> localDateTimes = userContext.get();
        localDateTimes.add(LocalDateTime.now());
    }
}
