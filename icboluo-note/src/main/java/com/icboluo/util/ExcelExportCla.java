package com.icboluo.util;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author icboluo
 * @since 2023-06-25 18:50
 */
public class ExcelExportCla<T> {

    private static final Map<Class<?>, Map<String, Field>> CLASS_NAME_FIELD_CACHE = new HashMap<>();

    private Map<String, Field> nameFieldMap;

    private Class<T> clazz;

    private List<String> sortList;

    public ExcelExportCla(Class<T> clazz) {
        this.clazz = clazz;
        this.toCache(clazz);
        this.nameFieldMap = CLASS_NAME_FIELD_CACHE.get(clazz);
    }

    private void toCache(Class<T> clazz) {

    }

    public List<List<String>> toHead() {
        List<List<String>> res = new ArrayList<>();
        return res;
    }

    public List<List<Object>> toBody() {
        List<List<Object>> res = new ArrayList<>();
        return res;
    }
}
