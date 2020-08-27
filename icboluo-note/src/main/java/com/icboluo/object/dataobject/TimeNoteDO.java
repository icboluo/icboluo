package com.icboluo.object.dataobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * time_note
 *
 * @author
 */
@Data
@ApiModel
public class TimeNoteDO implements Serializable {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("问题")
    private String problem;

    @ApiModelProperty("结果")
    private String result;

    @ApiModelProperty("完成次数")
    private Integer finishTime;

    @ApiModelProperty("所属范围")
    private String belongToScope;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("修改时间")
    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}
