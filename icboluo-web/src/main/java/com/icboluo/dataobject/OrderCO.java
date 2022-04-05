package com.icboluo.dataobject;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2021-29-27 0:29
 */
@Data
public class OrderCO {

    @NotNull
    private Integer id;

    @Min(10)
    private Integer code;

    private String unit;

    //    @Email
    private String customerEmail;

    private LocalDateTime acceptTime;
}
