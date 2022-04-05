package com.icboluo.object.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author icboluo
 * @since 2021-25-27 23:25
 */
@Data
public class FundCompleteDateBO {

    @JSONField(name = "LSJZList")
    private List<FundDateBO> fundDateList;
}
