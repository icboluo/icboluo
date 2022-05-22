package com.icboluo.entity.note;

import com.icboluo.object.view.FiledResultVO;

/**
 * @author icboluo
 * @since 2022-05-22 14:42
 */
public interface FiledResultInter {

    /**
     * 转换为根据字段查询结果的视图对象
     *
     * @return 根据字段查询结果视图
     */
    FiledResultVO toFiledResultView();
}
