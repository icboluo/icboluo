package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SystemConfig {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String configKey;

    private String configValue;

    private String addType;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
