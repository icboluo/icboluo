package com.icboluo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (FundAsyncRecord)实体类
 *
 * @author icboluo
 * @since 2021-06-08 00:09:31
 */
@Data
public class FundAsyncRecord implements Serializable {

    private String id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Serial
    private static final long serialVersionUID = -72420400963362802L;
}
