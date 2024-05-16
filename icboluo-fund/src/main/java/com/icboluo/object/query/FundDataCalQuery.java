package com.icboluo.object.query;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author icboluo
 * @since 2024-05-17 7:29
 */
@Data
public class FundDataCalQuery {
    String fundId;
    LocalDate startDate;
}
