package com.icboluo.common;

import com.alibaba.excel.converters.localdatetime.LocalDateTimeStringConverter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.icboluo.util.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * ExcelProperty(converter = LocalDateTimeConverter.class)
 * <p>时间转换器
 * <p>EasyExcel使用的反序列化工具并不是项目配置的，也不是fastjson，而是自己写了一个转换器，自定义的转换器配置了大部分的默认行为
 * <p>对于.xlsx文件，是存在格式的，例如cell是number类型，这样转换器可以根据不同的类型，使用不同的转换功能
 * <p>但是对于.csv文件，没有格式，时间类型难以使用默认转换器，需要自定义扩展
 */
public class LocalDateTimeConverter extends LocalDateTimeStringConverter {
    public final DateTimeFormatter yyyyMd1 = DateTimeFormatter.ofPattern("yyyy/M/d H:m");
    public final DateTimeFormatter yyyyMd2 = DateTimeFormatter.ofPattern("yyyy-M-d H:m");

    @Override
    public LocalDateTime convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                           GlobalConfiguration globalConfiguration) {
        try {
            // 首先，使用默认的转换器的转换功能
            return super.convertToJavaData(cellData, contentProperty, globalConfiguration);
        } catch (Exception ex) {
            if (cellData.getStringValue() != null) {
                // 如果失败，使用系统配置的反序列化工具（这里直接调用代码是不是不严谨，可以从jackson中提取出反序列化工具使用..）
                LocalDateTime localDateTime = DateUtil.allToDateTime(cellData.getStringValue());
                if (localDateTime == null) {
                    // 如果再失败，使用一种宽泛地反序列化功能，这种日期格式是非严格匹配，例如，分钟可以为01，可以以为1
                    List<DateTimeFormatter> formatters = Arrays.asList(yyyyMd1, yyyyMd2);
                    return DateUtil.allToDateTime(cellData.getStringValue(), formatters);
                } else {
                    return localDateTime;
                }
            } else if (cellData.getNumberValue() != null) {
                return DateUtil.numberValueToDateTime(cellData.getNumberValue());
            } else {
                return null;
            }
        }
    }
}
