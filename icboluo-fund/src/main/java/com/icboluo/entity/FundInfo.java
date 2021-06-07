package com.icboluo.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * (FundInfo)实体类
 *
 * @author icboluo
 * @since 2021-06-08 00:57:16
 */
@Data
public class FundInfo implements Serializable {
    /**
     * id
     */
    private String id;

    private String name;

    private static final long serialVersionUID = 337747966556460862L;
}
