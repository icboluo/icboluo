package com.icboluo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author icboluo
 */
@Data
public class Province implements Serializable {

    @TableId
    private Integer proId;

    private String proCode;

    private String proName;

    @Serial
    private static final long serialVersionUID = 880725100867624459L;
}
