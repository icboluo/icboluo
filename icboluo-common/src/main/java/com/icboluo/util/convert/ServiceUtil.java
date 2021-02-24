package com.icboluo.util.convert;

import com.icboluo.object.ArchivesVO;
import com.icboluo.util.BeanHelper;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author icboluo
 * @date 2021-41-24 21:41
 */
public class ServiceUtil {

    public static void main(String[] args) {
        List<ArchivesVO> list = new ArrayList<>();
        list.add(new ArchivesVO("123", "yiersan"));
        List<ConvertObj> convertObjList = new ArrayList<>();
        ConvertObj conObj = new ConvertObj<>();
        Function<ArchivesVO, String> get = ArchivesVO::getId;
        BiConsumer<ArchivesVO, String> set = ArchivesVO::setId;
        conObj.get = get;
        conObj.set = set;
        Function<Set, Map> function = (s) -> new ServiceUtil().aa(s);
        conObj.map = function;
        convertObjList.add(conObj);
        Function<ArchivesVO, ArchivesVO> con = (s) -> BeanHelper.copyProperties(s, ArchivesVO.class);
        List<ArchivesVO> convert = convert(
                list,
                convertObjList,
                con
        );
        System.out.println("convert = " + convert);
    }

    private Map<String, String> aa(Set<String> set) {
        HashMap<String, String> map = new HashMap<>();
        for (String s : set) {
            String val = s + "ddd";
            map.put(s, val);
        }
        return map;
    }

    public static <S, T> List<T> convert(List<S> sourceList, List<ConvertObj> convertObjList, Function<S, T> convert) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        ConvertObj convertObj = convertObjList.get(0);
        Set set = new HashSet<>();
        for (S s : sourceList) {
            Object o = convertObj.get.apply(s);
            set.add(o);
        }
        Function<Set, Map> conMap = convertObj.map;
        Map map = conMap.apply(set);
        List<T> list = new ArrayList<>();
        for (S s : sourceList) {
            T t = convert.apply(s);
            convertObj.set.accept(t, map.get(convertObj.get.apply(s)));
            list.add(t);
        }
        return list;
    }
}
