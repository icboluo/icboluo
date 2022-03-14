package com.icboluo.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 玩家(Monster)实体类
 *
 * @author makejava
 * @since 2022-03-14 22:22:29
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

    private static final long serialVersionUID = -85159120832417759L;
}

