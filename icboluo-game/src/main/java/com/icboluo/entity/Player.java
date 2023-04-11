package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 玩家(Player)实体类
 *
 * @author icboluo
 * @since 2022-03-15 22:48:56
 */
@Data
public class Player implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 姓名
     */
    private String name;

    @Serial
    private static final long serialVersionUID = -95089312827414366L;
}

