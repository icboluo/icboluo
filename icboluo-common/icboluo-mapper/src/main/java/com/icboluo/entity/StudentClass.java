package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author icboluo
 * @since 2026-06-01 00:24
 */
@Data
public class StudentClass {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer studentId;

    private Integer classId;
}
