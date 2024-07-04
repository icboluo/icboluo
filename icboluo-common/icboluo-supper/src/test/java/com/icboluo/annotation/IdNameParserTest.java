package com.icboluo.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.icboluo.BaseTest;
import com.icboluo.object.CodeName;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class IdNameParserTest extends BaseTest {

    @SpyBean
    private BaseI18nService baseI18nService;

    @Override
    protected void childBefore() {
        BaseI18n item1 = new BaseI18n();
        item1.setKey("id");
        item1.setName("14");
        BaseI18n item2 = new BaseI18n();
        item2.setKey("name");
        item2.setName("zhang shan");

        Mockito.doReturn(Arrays.asList(item1, item2)).when(baseI18nService).selectByKeys(Mockito.anyList());
    }

    @Test
    void parse() {
        StatusNumber item = new StatusNumber();
        CodeName codeName = new CodeName();
        codeName.setId("id");
        item.status = codeName;

        IdNameParser.parse(item);
        Assertions.assertEquals("14", item.getStatus().getName());
    }

    @Test
    void parseList() {
        StatusNumber item1 = new StatusNumber();
        CodeName codeName = new CodeName();
        codeName.setId("id");
        item1.status = codeName;
        StatusNumber item2 = new StatusNumber();
        CodeName codeName2 = new CodeName();
        codeName2.setId("name");
        item2.status = codeName2;

        List<StatusNumber> list = Arrays.asList(item1, item2);
        IdNameParser.parse(list);
        Assertions.assertEquals("14", list.get(0).getStatus().getName());
        Assertions.assertEquals("zhang shan", list.get(1).getStatus().getName());
    }

    @Test
    void parseMList() {
        StatusNumber data1 = new StatusNumber();
        CodeName codeName1 = new CodeName("id", null);
        data1.setStatus(codeName1);

        StatusNumber data2 = new StatusNumber();
        CodeName codeName2 = new CodeName("name", null);
        data2.setStatus(codeName2);

        ExclusiveSupport data = new ExclusiveSupport();
        StatusNumber snp = new StatusNumber();
        snp.setStatus(codeName1);
        StatusList statusList = new StatusList();
        statusList.setStatusList(Collections.singletonList(snp));
        data.setItemList(statusList);

        Coordinate coordinate = new Coordinate();
        coordinate.xCoordinate = codeName2;
        LineChart lineChart = new LineChart();
        lineChart.coordinates = Collections.singletonList(coordinate);
        data.setItemTime(lineChart);

        IdNameParser.parse(data);
        Assertions.assertEquals("14", data.getItemList().getStatusList().get(0).getStatus().getName());
        Assertions.assertEquals("zhang shan", data.getItemTime().getCoordinates().get(0).getXCoordinate().getName());
    }

    @Data
    static class ExclusiveSupport {
        /**
         * 状态
         */
        @IdToName
        StatusList itemList;

        /**
         * 时间
         */
        @IdToName
        LineChart itemTime;
    }

    @Data
    static class StatusList {
        /**
         * 列表
         */
        @IdToName(data = IdNameEnum.I18N)
        List<StatusNumber> statusList;

        /**
         * 总数
         */
        Integer total;
    }

    /**
     * 坐标列表
     */
    @Data
    static class LineChart {
        @IdToName
        List<Coordinate> coordinates;

        Integer number;
    }

    @Data
    static class StatusNumber {
        @IdToName(data = IdNameEnum.I18N)
        CodeName status;

        Integer number;
    }


    /**
     * 坐标
     */
    @Data
    static class Coordinate {
        /**
         * x坐标
         */
        @JsonProperty(value = "x")
        @IdToName(data = IdNameEnum.I18N)
        CodeName xCoordinate;

        /**
         * y坐标
         */
        Object y;
    }
}
