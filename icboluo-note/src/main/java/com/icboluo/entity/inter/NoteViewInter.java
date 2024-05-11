package com.icboluo.entity.inter;

import com.icboluo.object.view.NoteVO;

/**
 * @author icboluo
 * @since 2022-05-22 14:42
 */
public interface NoteViewInter {
    /**
     * 转换为视图对象
     *
     * @return 视图对象
     */
    NoteVO toView();
}
