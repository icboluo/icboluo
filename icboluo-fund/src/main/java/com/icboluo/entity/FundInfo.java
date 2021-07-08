package com.icboluo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (FundInfo)实体类
 *
 * @author icboluo
 * @since 2021-07-08 20:23:18
 */
@Data
public class FundInfo implements Serializable {
    /**
     * id
     */
    private String id;

    private String name;

    private static final long serialVersionUID = -83256998777520315L;
}
