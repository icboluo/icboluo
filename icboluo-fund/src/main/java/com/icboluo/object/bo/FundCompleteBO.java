package com.icboluo.object.bo;

import lombok.Data;

/**
 * 完整的基金对象
 *
 * @author icboluo
 * @since 2021-22-27 23:22
 */
@Data
@SuppressWarnings("all")
public class FundCompleteBO {

    private FundCompleteDateBO Data;
    private String FundType;
    private String SYType;
    private String isNewType;
    private String Feature;
    private Integer ErrCode;
    private String ErrMsg;
    private Integer TotalCount;
    private String Expansion;
    private Integer PageSize;
    private Integer PageIndex;
}
