package com.icboluo.service.impl;

import com.icboluo.service.AddUpdateDel;
import com.icboluo.service.CommonService;
import com.spring.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author icboluo
 * @since 2021-11-07 14:46
 */
@Component("commonServiceImpl")
public class CommonServiceImpl implements CommonService {

    @Override
    public <T> void addUpdateDel(AddUpdateDel<T> param) {
        List<T> clientList = param.getClientList();
        List<T> dbList = param.getDbList();
        BiPredicate<T, T> isSame = param.getIsSame();
        BiPredicate<T, T> equals = param.getEquals();
        List<T> addList = new ArrayList<>();
        List<T> updateClientList = new ArrayList<>();
        List<T> updateDbList = new ArrayList<>();
        List<T> removeList = new ArrayList<>();
        for (T db : dbList) {
            boolean isFind = false;
            for (T client : clientList) {
                if (!isSame.test(db, client)) {
                    continue;
                }
                isFind = true;
                if (!equals.test(db, client)) {
                    updateClientList.add(client);
                    updateDbList.add(db);
                }
                break;
            }
            if (!isFind) {
                removeList.add(db);
            }
        }

        for (T client : clientList) {
            boolean isFind = false;
            for (T db : dbList) {
                if (!isSame.test(db, client)) {
                    continue;
                }
                isFind = true;
                break;
            }
            if (!isFind) {
                addList.add(client);
            }
        }
    }
}
