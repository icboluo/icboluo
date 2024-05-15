package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * (FundData)实体类
 *
 * @author icboluo
 * @since 2021-07-08 20:38:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundData implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 基金id
     */
    private String fundId;
    /**
     * 日增长率
     */
    private BigDecimal increaseRateDay;
    /**
     * 申购状态
     */
    private String subscribeStatus;
    /**
     * 净值日期
     */
    private LocalDate netValueDate;
    /**
     * 单位净值
     */
    private BigDecimal netAssetValue;
    /**
     * 累计净值
     */
    private BigDecimal netValueCumulative;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @Serial
    private static final long serialVersionUID = 932315031704088938L;
}
