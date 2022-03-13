package com.icboluo.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 玩家(Player)实体类
 *
 * @author makejava
 * @since 2022-03-13 03:40:14
 */
@Data
public class Player implements Serializable {
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
    /**
     * 最大血量
     */
    private Integer maxBlood;

    private static final long serialVersionUID = -48102019411451812L;
}

