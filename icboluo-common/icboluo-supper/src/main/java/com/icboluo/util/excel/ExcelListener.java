package com.icboluo.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
public class ExcelListener<T> extends AnalysisEventListener<T> {
    /**
     * 数据列表
     */
    private final List<T> list = new ArrayList<>();

    /**
     * 当前sheet名称
     *
     * @see ValidHeadExcelListener sheetName当使用该子类的时候会被赋值
     * 这些属性本来是在子类中的，提取到父类中的唯一目的就是方便调用，但是在设计上有不合理的地方
     */
    protected String sheetName;

    /**
     * 当前sheet页表头有多少行，默认有1行表头
     */
    protected int head = 1;

    /**
     * 当前sheet页导入的数据类型
     */
    protected Class<T> clazz;

    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // Not need impl Method
    }

    /**
     * 根据字段值去重，并且取最后一条
     *
     * @param function 获取字段的函数（或者获取联合索引的函数
     * @return 去重后的列表
     */
    public List<T> getDistinctList(Function<T, Object> function) {
        LinkedHashMap<Object, T> map = list.stream()
                .collect(Collectors.toMap(function, Function.identity(), (fir, sec) -> sec, LinkedHashMap::new));
        return new ArrayList<>(map.values());
    }

    /**
     * 获取重复数据的msg
     *
     * @param getUk 根据item获取uk的函数
     * @return 重复数据的map k：uk； value：row num
     */
    public Map<String, List<Integer>> getRepeatMsg(Function<T, String> getUk) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String uk = getUk.apply(list.get(i));
            if (!map.containsKey(uk)) {
                ArrayList<Integer> value = new ArrayList<>();
                value.add(i);
                map.put(uk, value);
            } else {
                map.get(uk).add(i);
            }
        }
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
