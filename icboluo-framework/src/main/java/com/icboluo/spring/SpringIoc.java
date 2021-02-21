package com.icboluo.spring;

import com.icboluo.object.ArchivesVO;

import javax.annotation.Resource;

/**
 * @author icboluo
 * @date 2021-36-19 19:36
 */
public class SpringIoc {
    /**
     * spring 的注入方式
     */
    @Resource
    private ArchivesVO archivesVO;

    /**
     * 相当于
     */
/*    private ArchivesVO archives = getArchivesVO();

    private singleton getArchivesVO() {
        // 单例注入 map：ioc容器
        if (map.get(ArchivesVO) == null) {
            map.put(new ArchivesVO());
        }
        return map.get(ArchivesVO);
    }

    private prototype getArchivesVO() {
//多例注入
        return new ArchivesVO();
    }*/
}
