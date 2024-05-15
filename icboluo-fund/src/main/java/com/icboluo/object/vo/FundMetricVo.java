package com.icboluo.object.vo;

import com.icboluo.common.Desc;
import com.icboluo.common.FundMetricEnum;
import com.icboluo.entity.FundData;
import com.icboluo.object.Archives;
import com.icboluo.object.bo.FundMetricBo;
import lombok.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author icboluo
 * @since 2024-05-15 19:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundMetricVo {
    private List<FundData> dataList;
    private List<List<FundData>> doubleDataList;
    /**
     * 指标名称
     */
    private String metricName;
    /**
     * 指标描述
     */
    private String desc;
    /**
     * 指标值
     */
    private BigDecimal number;

    private List<Archives<String, Object>> itemList;

    public static class FundMetricVoBuilder {
        private String metricName;
        private String desc;
        private BigDecimal number;
        private List<Archives<String, Object>> itemList;

        FundMetricVoBuilder() {
        }

        @SneakyThrows
        public FundMetricVoBuilder metric(final FundMetricEnum metric) {
            this.metricName = metric.name();
            Field field = metric.getClass().getField(metric.name());
            Desc annotation = field.getAnnotation(Desc.class);
            assert annotation != null;
            this.desc = annotation.value();
            return this;
        }

        public FundMetricVoBuilder businessData(final FundMetricBo business) {
            if (business.getDataList() != null) {
                this.dataList = business.getDataList().stream().sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate())).toList();
            }
            if (business.getDoubleDataList() != null) {
                this.doubleDataList = business.getDoubleDataList().stream().map(arr -> arr.stream().sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate())).toList()).toList();
            }
            this.number = business.getAllAvg();
            this.itemList = business.toItemList();
            return this;
        }
    }
}
