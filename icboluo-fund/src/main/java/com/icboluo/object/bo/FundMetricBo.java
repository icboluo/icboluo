package com.icboluo.object.bo;

import com.icboluo.entity.FundData;
import com.icboluo.object.Archives;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2024-05-15 19:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundMetricBo {
    private List<FundData> dataList;

    private List<List<FundData>> doubleDataList;

    private Long count;

    private BigDecimal avg;

    private Double min;

    private Double max;


    /**
     * 净增长值
     */
    private BigDecimal netIncreaseValue;


    public FundMetricBo(List<FundData> list) {
        this.dataList = list;
        DoubleSummaryStatistics summaryStatistics = list.stream()
                .map(FundData::getIncreaseRateDay)
                .filter(item -> !ObjectUtils.isEmpty(item))
                .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));

        this.count = summaryStatistics.getCount();
        this.avg = BigDecimal.valueOf(summaryStatistics.getAverage()).setScale(4, RoundingMode.HALF_UP);
        this.min = summaryStatistics.getMin();
        this.max = summaryStatistics.getMax();
        calNetIncreaseValue();
    }

    public FundMetricBo(List<List<FundData>> lists, int i) {
        List<FundData> list = lists.stream()
                .flatMap(Collection::stream)
                .toList();
        DoubleSummaryStatistics summaryStatistics = list.stream()
                .map(FundData::getIncreaseRateDay)
                .filter(item -> !ObjectUtils.isEmpty(item))
                .collect(Collectors.summarizingDouble(BigDecimal::doubleValue));

        this.doubleDataList = lists;
        this.count = summaryStatistics.getCount();
        this.avg = BigDecimal.valueOf(summaryStatistics.getAverage()).setScale(4, RoundingMode.HALF_UP);
        this.min = summaryStatistics.getMin();
        this.max = summaryStatistics.getMax();
    }

    public List<Archives<String, Object>> toItemList() {
        List<Archives<String, Object>> list = new ArrayList<>();
        list.add(new Archives<>("avg", avg));
        list.add(new Archives<>("min", min));
        list.add(new Archives<>("max", max));
        return list;
    }

    private void calNetIncreaseValue() {
        BigDecimal start = BigDecimal.ONE;
        if (dataList != null) {
            for (FundData fundData : dataList) {
                start = start.multiply(fundData.getIncreaseRateDay());
            }
            netIncreaseValue = start;
        }
    }
}
