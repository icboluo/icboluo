package com.icboluo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 玩家(Monster)实体类
 *
 * @author icboluo
 * @since 2022-03-15 23:00:20
 */
@Data
public class Monster implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 血量
     */
    private Integer blood;
    /**
     * 攻击
     */
    private Integer attack;
    /**
     * 最大血量
     */
    private Integer maxBlood;
    /**
     * 姓名
     */
    private String name;

    @Serial
    private static final long serialVersionUID = -75975117175722732L;
}

