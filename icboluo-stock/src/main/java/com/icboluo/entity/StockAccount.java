package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockAccount implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 赛季id
     */
    private Integer seasonId;
    /**
     * 玩家名称
     */
    private String playerName;
    /**
     * 可用资金
     */
    private BigDecimal availableFund;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
