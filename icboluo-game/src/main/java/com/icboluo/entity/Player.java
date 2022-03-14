package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 玩家(Player)实体类
 *
 * @author makejava
 * @since 2022-03-14 22:22:41
 */
@Data
public class Player implements Serializable {
    /**
     * id TODO 加这个真的难受，不加没有返回值，怎么处理
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

    private static final long serialVersionUID = -29898007683118569L;
}

