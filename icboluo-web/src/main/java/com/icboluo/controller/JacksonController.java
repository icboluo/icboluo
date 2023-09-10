package com.icboluo.controller;

import com.icboluo.dataobject.JacksonVO;
import com.icboluo.object.CodeName;
import com.icboluo.object.IdName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author icboluo
 * @since 2023-09-10 20:07
 */
@RestController
@RequestMapping("jackson")
@Slf4j
public class JacksonController {

    @GetMapping("localDateTime")
    public LocalDateTime[] localDateTime(@RequestBody LocalDateTime[] arr) {
        log.info(Arrays.toString(arr));
        return arr;
    }

    @GetMapping("localDate")
    public LocalDate[] localDate(@RequestBody LocalDate[] arr) {
        log.info(Arrays.toString(arr));
        return arr;
    }

    @GetMapping("customize")
    public JacksonVO customize() {
        JacksonVO view = new JacksonVO();
        view.setLevel(new IdName(14, "级别3"));
        view.setStatus(new IdName(null, "处理中"));
        view.setCountry(new CodeName(null, "中国"));
        view.setAvg(BigDecimal.valueOf(77.70));
        view.setTotal(BigDecimal.valueOf(66.60));
        view.setCountryList(Collections.singletonList(view.getCountry()));
        view.setStatusList(Arrays.asList(view.getStatus(), view.getLevel()));
        return view;
    }
}
