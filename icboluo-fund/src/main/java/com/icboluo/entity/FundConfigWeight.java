package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 基金--配置--权重(FundConfigWeight)实体类
 *
 * @author icboluo
 * @since 2024-05-15 18:41:03
 */
@Data
public class FundConfigWeight implements Serializable {
    /**
     * key
     */
    @TableId
    private String key;

    /**
     * qua
     */
    private Integer val;

    /**
     * 描述
     */
    private String desc;

    private static final long serialVersionUID = -14980936607925659L;
}

