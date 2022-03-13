package com.icboluo.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 玩家(Monster)实体类
 *
 * @author makejava
 * @since 2022-03-13 02:52:18
 */
@Data
public class Monster implements Serializable {
    private static final long serialVersionUID = 800852238874200564L;
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
}

