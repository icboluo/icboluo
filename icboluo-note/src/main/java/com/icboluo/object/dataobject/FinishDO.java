package com.icboluo.object.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * finish
 * @author lp
 */
@Data
public class FinishDO implements Serializable {
    private Integer id;

    /**
     * 问题
     */
    private String problem;

    /**
     * 结果
     */
    private String result;

    private String belongToScope;

    private Date gmtCreate;

    private Date gmtFirstCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}
