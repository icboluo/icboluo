package com.icboluo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 玩家(DiePlayer)实体类
 *
 * @author icboluo
 * @since 2022-03-14 23:11:11
 */
@Data
public class DiePlayer implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 攻击
     */
    private Integer attack;
    /**
     * 血量
     */
    private Integer blood;
    /**
     * 最大血量
     */
    private Integer maxBlood;
    /**
     * 经验
     */
    private Integer experience;
    /**
     * 总经验
     */
    private Integer totalExperience;
    /**
     * 级别
     */
    private Integer level;

    @Serial
    private static final long serialVersionUID = 910295010867363556L;
}

