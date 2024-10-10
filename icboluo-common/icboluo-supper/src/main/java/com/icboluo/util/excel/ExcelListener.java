package com.icboluo.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.BeanUtil;
import com.icboluo.util.I18nException;
import com.icboluo.util.SpringUtil;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>Excel 简单导入，单个监听器仅能监听一个sheet页导入的数据
 * <p>使用方式如下
 * <pre>{@code List<Client> list=ExcelUtil.getList(new ValidHeadBodyListener<>(Client.class)); }<pre/>
 * <p>该类设计成抽象类，一般不会单独使用，请使用该类的子类
 *
 * @author icboluo
 * @see MultipleSheetTemplate 如果导入多个sheet页，可以使用该模版实现
 * @since 2023-08-03 21:08
 */
@Getter
public abstract class ExcelListener<T> extends AnalysisEventListener<T> {
    protected static final MessageSource MESSAGE_SOURCE = SpringUtil.getBean(MessageSource.class);

    ExcelEntity<T> excelEntity;

    protected ExcelListener() {
        excelEntity = new ExcelEntity<>();
        excelEntity.clazz = BeanUtil.cast(Object.class);
    }

    protected ExcelListener(Class<T> clazz, ExcelEntity<T> entity) {
        excelEntity = entity;
        excelEntity.clazz = clazz;
        excelEntity.resolve = new ExcelExportResolve<>(clazz);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (!StringUtils.hasText(excelEntity.sheetName)) {
            excelEntity.sheetName = context.readSheetHolder().getSheetName();
        }
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        excelEntity.list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // Not need impl Method
    }

    public Class<T> getClazz() {
        return excelEntity.clazz;
    }

    public int getHeadRowNumber() {
        return excelEntity.headRowNumber;
    }

    public void setHeadRowNumber(int headRowNumber) {
        excelEntity.headRowNumber = headRowNumber;
    }

    public List<T> getList() {
        return excelEntity.list;
    }

    public String sheetName() {
        return excelEntity.sheetName;
    }

    public void sheetName(String sheetName) {
        excelEntity.sheetName = sheetName;
    }

    /**
     * 根据字段值去重，并且取最后一条
     *
     * @param fun 获取字段的函数（或者获取联合索引的函数
     * @return 去重后的列表
     */
    public List<T> getDistinctList(Function<T, Object> fun) {
        return excelEntity.getDistinctList(fun);
    }

    /**
     * 获取重复数据的msg
     *
     * @param getUk 根据item获取uk的函数
     * @return 重复数据的map k：uk ，value：row num
     */
    public Map<String, List<Integer>> getRepeatMsg(Function<T, String> getUk) {
        return excelEntity.getRepeatMsg(getUk);
    }

    public void validRepeat(Function<T, String> getUk) {
        excelEntity.validRepeat(getUk);
    }

    public void validEmpty() {
        if (excelEntity.list.isEmpty()) {
            throw new I18nException(ReEnum.EXCEL_DATA_IS_EMPTY);
        }
    }

    public void validBody() {

    }

    public int getMaxLine() {
        return ExcelExportResolve.getIndexField(excelEntity.clazz).lastKey();
    }

    public Integer getHead() {
        return excelEntity.headRowNumber;
    }
}
