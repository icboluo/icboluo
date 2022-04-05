package com.icboluo.service;

import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 202110-24 21:23
 */
public interface CodeService {

    int codeToId(int code);

    Map<Integer, Integer> codeToId(List<Integer> code);

    List<Integer> codeToIdList(List<Integer> code);
}
