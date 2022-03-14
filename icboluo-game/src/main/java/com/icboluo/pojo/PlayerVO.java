package com.icboluo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 玩家(Player)实体类
 *
 * @author makejava
 * @since 2022-03-13 12:17:18
 */
@Data
public class PlayerVO implements Serializable {
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
    private String level;
    /**
     * 最大血量
     */
    private Integer maxBlood;
    /**
     * 年龄
     */
    private Integer age;

    private static final long serialVersionUID = 988155755708954717L;
}
