package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 股票基本信息实体类
 */
@Data
public class StockInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 股票代码
     * （主键，6位数字，如 000001）
     */
    @TableId
    String stockCode;
    /**
     * 股票名称（如 "平安银行"）
     */
    String stockName;
}
