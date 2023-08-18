package com.icboluo.entity.note;

import com.icboluo.common.Constant;
import com.icboluo.object.view.FiledResultVO;
import com.icboluo.util.BeanUtil;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author icboluo
 */
@Data
public class FinishDO implements FiledResultInter, Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 问题
     */
    private String problem;

    /**
     * 结果
     */
    private String result;

    /**
     * 所属范围
     */
    private String belongToScope;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 源头数据创建时间
     */
    private LocalDateTime gmtFirstCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public FiledResultVO toFiledResultView() {
        FiledResultVO filedResult = BeanUtil.copyProperties(this, FiledResultVO.class);
        filedResult.setType(Constant.FINISH_TYPE);
        return filedResult;
    }
}
