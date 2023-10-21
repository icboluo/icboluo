package com.icboluo.entity.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author icboluo
 */
@Data
public class User implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * code
     */
    private Integer code;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreat;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 名称
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private Integer tel;

    @Serial
    private static final long serialVersionUID = 1L;
}
