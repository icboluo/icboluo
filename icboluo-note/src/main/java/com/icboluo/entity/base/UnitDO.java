package com.icboluo.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 *
 */
@Data
public class UnitDO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 单位编码
     */
    private String code;

    /**
     * 单位名称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}
