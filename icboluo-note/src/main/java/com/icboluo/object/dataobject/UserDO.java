package com.icboluo.object.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
@Data
public class UserDO implements Serializable {
    private Integer id;

    private Integer code;

    private Date gmtCreat;

    private Date gmtModified;

    private String name;

    /**
     * 昵称
     */
    private String nickName;

    private String password;

    /**
     * 电话
     */
    private Integer tel;

    private static final long serialVersionUID = 1L;
}
