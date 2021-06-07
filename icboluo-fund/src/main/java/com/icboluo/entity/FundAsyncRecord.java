package com.icboluo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;

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

    private static final long serialVersionUID = -72420400963362802L;
}
