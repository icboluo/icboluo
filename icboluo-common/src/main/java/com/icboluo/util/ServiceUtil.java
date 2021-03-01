package com.icboluo.util;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author icboluo
 * @date 2021-40-01 21:40
 */
public class ServiceUtil {
    /**
     * 主子表新增
     */
    public static <M, S> void add(Consumer<M> mainInsert, Consumer<List<S>> subListInsert, M main, List<S> sub) {
        mainInsert.accept(main);
        if (!CollectionUtils.isEmpty(sub)) {
            subListInsert.accept(sub);
        }
    }

    public static <M, S, K> void update(Consumer<M> mainUpdate, Consumer<List<S>> subListUpdate, Consumer<K> subDel, M main, List<S> sub, K id) {
        mainUpdate.accept(main);
        subDel.accept(id);
        if (!CollectionUtils.isEmpty(sub)) {
            subListUpdate.accept(sub);
        }
    }
}
