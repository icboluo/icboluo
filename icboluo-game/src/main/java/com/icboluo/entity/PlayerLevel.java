package com.icboluo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (PlayerLevel)实体类
 *
 * @author icboluo
 * @since 2022-03-15 22:06:25
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
    /**
     * 经验
     */
    private Integer experience;
    /**
     * 增加攻击
     */
    private Integer increaseAttack;
    /**
     * 增加血量
     */
    private Integer increaseBlood;
    /**
     * 年龄
     */
    private Integer age;

    @Serial
    private static final long serialVersionUID = 696671307215741211L;
}

