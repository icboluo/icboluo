package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ProjectConfig {
@TableId(type = IdType.AUTO)
private Integer id;
    /**
     * 项目id
     */
    private Integer pid;
    /**
     * key，key在项目中为唯一值
     */
    private String key;
    private String value;
}
