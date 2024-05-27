package com.icboluo.object.vo;

import com.icboluo.common.Desc;
import com.icboluo.common.FundMetricEnum;
import com.icboluo.entity.FundData;
import com.icboluo.object.Archives;
import com.icboluo.object.bo.FundMetricBo;
import lombok.*;
import org.springframework.util.StringUtils;

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

    private String tip;
    /**
     * 指标值
     */
    private BigDecimal number;

    /**
     * 是否推荐
     */
    private boolean isRecommend;

    private List<Archives<String, Object>> itemList;

    public static class FundMetricVoBuilder {
        private String metricName;
        private String desc;
        private String tip;
        private BigDecimal number;
        private List<Archives<String, Object>> itemList;

        private FundMetricEnum metricEnum;

        FundMetricVoBuilder() {
        }

        @SneakyThrows
        public FundMetricVoBuilder metric(final FundMetricEnum metric) {
            this.metricEnum = metric;
            this.metricName = metric.name();
            Field field = metric.getClass().getField(metric.name());
            Desc anno = field.getAnnotation(Desc.class);
            assert anno != null;
            this.desc = anno.value();
            this.tip = StringUtils.hasText(anno.tip()) ? anno.tip() : metricName;

            return this;
        }

        @SneakyThrows
        public FundMetricVoBuilder businessData(final FundMetricBo business) {
//            if (business.getDataList() != null) {
//                this.dataList = business.getDataList().stream().sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate())).toList();
//            }
//            if (business.getDoubleDataList() != null) {
//                this.doubleDataList = business.getDoubleDataList().stream().map(arr -> arr.stream().sorted((a, b) -> b.getNetValueDate().compareTo(a.getNetValueDate())).toList()).toList();
//            }
            this.number = business.getNetIncreaseValue();
            this.itemList = business.toItemList();

            Field field = metricEnum.getClass().getField(metricEnum.name());
            Desc anno = field.getAnnotation(Desc.class);
            if (anno.recommend() == Desc.Recommend.MORE_THAN) {
                this.isRecommend = number.compareTo(BigDecimal.ZERO) > 0;
            } else {
                this.isRecommend = number.compareTo(BigDecimal.ZERO) < 0;
            }
            return this;
        }
    }
}
