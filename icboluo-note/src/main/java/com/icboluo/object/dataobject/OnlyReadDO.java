package com.icboluo.object.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
@Data
public class OnlyReadDO implements Serializable {
    private Integer id;

    /**
     * 问题
     */
    private String problem;

    /**
     * 结果
     */
    private String result;

    /**
     * 完成次数
     */
    private Integer finishTime;

    private String belongToScope;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}
