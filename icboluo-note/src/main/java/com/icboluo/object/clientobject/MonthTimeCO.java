package com.icboluo.object.clientobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lp
 */
@Data
public class MonthTimeCO {

    @ApiModelProperty("所属范围")
    private String belongToScope;

    @ApiModelProperty("修改时间")
    private Date gmtModified;

    @ApiModelProperty("问题")
    private String problem;

    @ApiModelProperty("完成次数")
    private Integer finishTime;

    @ApiModelProperty("结果")
    private String result;

}
