package com.icboluo.dataobject;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

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

    /**
     * JDK的Email注解是空校验，如果校验邮件，需要使用Spring的Email注解
     */
    @Email
    private String customerEmail;

    private LocalDateTime acceptTime;
}
