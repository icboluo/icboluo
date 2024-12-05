package com.icboluo.util.excel;

import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.I18nException;
import com.icboluo.util.SpringUtil;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

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
    protected ExcelResolve<T> resolve;
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
            int rowNo = i + headRowNumber + 1;
            String uk = getUk.apply(list.get(i));
            if (!map.containsKey(uk)) {
                List<Integer> value = new ArrayList<>();
                value.add(rowNo);
                map.put(uk, value);
            } else {
                map.get(uk).add(rowNo);
            }
        }
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void validRepeat(Function<T, String> getUk) {
        Map<String, List<Integer>> repeatMsg = getRepeatMsg(getUk);
        List<String> msg = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : repeatMsg.entrySet()) {
            String temp = MESSAGE_SOURCE.getMessage(ReEnum.EXCEL_REPEAT.getMsg(),
                    new Object[]{entry.getValue()}, LocaleContextHolder.getLocale());
            msg.add(temp);
        }
        if (!msg.isEmpty()) {
            throw new I18nException(String.join("<br/><br/>", msg));
        }
    }
}
