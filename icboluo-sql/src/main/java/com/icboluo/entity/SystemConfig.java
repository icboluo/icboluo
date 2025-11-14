package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author icboluo
 * @since 2025-11-14 12:58
 */
@Data
public class SystemConfig {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String key;

    private String value;
}
