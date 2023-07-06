package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import lombok.Data;

/**
 * (Area)实体类
 *
 * @author icboluo
 * @since 2023-07-07 05:54:32
 */
@Data
public class Area implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer areId;


    private String areCode;


    private String areName;


    private String cityCode;

    private static final long serialVersionUID = 804623409733231119L;
}

