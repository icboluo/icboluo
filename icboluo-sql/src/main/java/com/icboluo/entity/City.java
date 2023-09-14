package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (City)实体类
 *
 * @author icboluo
 * @since 2023-07-07 06:01:41
 */
@Data
public class City implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer cityId;


    private String cityCode;


    private String cityName;


    private String proCode;

    @Serial
    private static final long serialVersionUID = -48411998790233051L;
}

