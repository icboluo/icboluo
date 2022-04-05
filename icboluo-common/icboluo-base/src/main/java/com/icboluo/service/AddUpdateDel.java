package com.icboluo.service;

import lombok.Data;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author icboluo
 * @since 2021-11-07 14:49
 */
@Data
public class AddUpdateDel<T> {

    private List<T> clientList;

    private List<T> dbList;

    private BiPredicate<T, T> isSame;

    private BiPredicate<T, T> equals;
}
