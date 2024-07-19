package com.icboluo.util.excel;

import com.icboluo.annotation.Excel;
import com.icboluo.util.SpringUtil;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.MessageSource;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Excel 实体类
 *
 * @author icboluo
 * @since 2024-07-19 下午6:05
 */
@Data

public class ExcelEntity<T> {

    private static final MessageSource MESSAGE_SOURCE = SpringUtil.getBean(MessageSource.class);
    protected ExcelExportResolve<T> resolve;
    /**
     * 当前sheet名称
     *
     * @see ValidHeadListener sheetName当使用该子类的时候会被赋值
     * 这些属性本来是在子类中的，提取到父类中的唯一目的就是方便调用，但是在设计上有不合理的地方
     */
    protected String sheetName;
    /**
     * 当前sheet页表头有多少行，默认有1行表头
     */
    protected int headRowNumber = 1;
    /**
     * 当前sheet页导入的数据类型
     */
    protected Class<T> clazz;
    /**
     * 数据列表
     */
    protected final List<T> list = new ArrayList<>();

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
                List<Integer> value = new ArrayList<>();
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

    public void validRepeat(Function<T, String> getUk) {

    }
}
