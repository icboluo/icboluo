package com.icboluo.dataobject;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2021-29-27 0:29
 */
public class OrderCO {

    @NotNull
    private Integer id;

    private Integer code;

    private String unit;

//    @Email
    private String customerEmail;

    private LocalDateTime acceptTime;
}
