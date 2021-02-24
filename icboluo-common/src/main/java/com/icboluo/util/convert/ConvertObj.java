package com.icboluo.util.convert;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author icboluo
 * @date 2021-49-24 21:49
 */
public class ConvertObj<K, V> {

    Function<K, V> get;

    BiConsumer<K, V> set;

    Function<Set<K>, Map<K, V>> map;
}
