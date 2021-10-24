package com.icboluo.common;


import com.icboluo.enumerate.LanguageEnum;
import com.icboluo.enumerate.StatusEnum;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class StatusEnumTest {

    @Test
    void findByStatus() {
        Optional<StatusEnum> byStatus = StatusEnum.findByStatus("1");
        System.out.println(byStatus.orElse(null));
    }

    @Test
    void findChineseNameByStatus() {
        String statusName = StatusEnum.findNameByStatusLanguage(1, LanguageEnum.ENGLISH);
        System.out.println(statusName);
    }
}
