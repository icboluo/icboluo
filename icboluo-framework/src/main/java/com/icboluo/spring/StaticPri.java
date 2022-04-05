package com.icboluo.spring;

import com.icboluo.object.ArchivesVO;

import javax.annotation.Resource;

/**
 * 成员方法调用静态变量
 *
 * @author icboluo
 * @since 2021-52-22 21:52
 */
public class StaticPri {

    private static ArchivesVO archivesVO;

    @Resource
    private void setArchivesVO(ArchivesVO archivesVO) {
        StaticPri.archivesVO = archivesVO;
    }

    /**
     * 静态内容调成员内容
     */
    private static void staticComm() {
//      但是new 出来的对象在Spring中，如果有DI则无法使用，会是null值
        StaticPri staticPri = new StaticPri();
        staticPri.setArchivesVO(null);
    }
}
