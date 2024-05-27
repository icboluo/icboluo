package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (FundAttention)实体类
 *
 * @author icboluo
 * @since 2021-05-27 23:04:58
 */
@Data
public class FundAttention implements Serializable {
    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 基金名称
     */
    private String name;

    @Serial
    private static final long serialVersionUID = 187840623183179850L;
}
