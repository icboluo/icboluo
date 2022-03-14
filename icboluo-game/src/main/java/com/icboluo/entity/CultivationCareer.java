package com.icboluo.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 修仙生涯(CultivationCareer)实体类
 *
 * @author icboluo
 * @since 2022-03-15 00:50:25
 */
@Data
public class CultivationCareer implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 玩家id
     */
    private Integer playerId;
    /**
     * 操作
     */
    private String oper;

    private static final long serialVersionUID = 593617170844456503L;
}

