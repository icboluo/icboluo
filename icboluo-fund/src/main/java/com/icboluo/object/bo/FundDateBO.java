package com.icboluo.object.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.icboluo.entity.FundData;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 单个基金日期对象
 *
 * @author icboluo
 * @date 2021-26-27 23:26
 */
@Data
@SuppressWarnings("all")
public class FundDateBO {

    /**
     * 净值日期
     */
    @JSONField(name = "FSRQ")
    private LocalDateTime netValueDate;
    /**
     * 单位净值
     */
    @JSONField(name = "DWJZ")
    private String netAssetValue;
    /**
     * 累计净值
     */
    @JSONField(name = "LJJZ")
    private String netValueCumulative;
    private String SDATE;
    private String ACTUALSYI;
    private String NAVTYPE;
    /**
     * 日增长率
     */
    @JSONField(name = "JZZZL")
    private String increaseRateDay;
    /**
     * 申购状态
     */
    @JSONField(name = "SGZT")
    private String subscribeStatus;
    private String SHZT;
    private String FHFCZ;
    private String FHFCBZ;
    private String DTYPE;
    private String FHSP;

    public FundData businessToData(String fundId) {
        FundData data = new FundData();
        data.setFundId(fundId);
        data.setNetValueDate(netValueDate);
        data.setNetAssetValue(netAssetValue);
        data.setNetValueCumulative(netValueCumulative);
        data.setIncreaseRateDay(increaseRateDay);
        data.setSubscribeStatus(subscribeStatus);
        data.setCreateTime(LocalDateTime.now());
        data.setUpdateTime(LocalDateTime.now());
        return data;
    }
}
