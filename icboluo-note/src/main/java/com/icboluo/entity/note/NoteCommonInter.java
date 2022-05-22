package com.icboluo.entity.note;

import java.time.LocalDateTime;

/**
 * @author icboluo
 * @since 2022-05-22 15:33
 */
public interface NoteCommonInter {
    /**
     * 获取修改时间
     *
     * @return 修改时间
     */
    LocalDateTime getGmtModified();

    void setId(Integer id);

    void setGmtModified(LocalDateTime gmtModified);
}
