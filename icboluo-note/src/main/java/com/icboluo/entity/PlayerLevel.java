package com.icboluo.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * (PlayerLevel)实体类
 *
 * @author makejava
 * @since 2022-03-13 12:17:47
 */
@Data
public class PlayerLevel implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 级别
     */
    private String level;

    private static final long serialVersionUID = 860960052952555557L;
}

