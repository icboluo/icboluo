package com.icboluo.entity.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author
 * 软件密码
 */
@Data
public class SoftwarePasswordDO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 软件名称
     */
    private String software;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮件
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    @Serial
    private static final long serialVersionUID = 1L;
}
