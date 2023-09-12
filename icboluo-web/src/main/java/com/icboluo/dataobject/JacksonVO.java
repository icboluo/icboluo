package com.icboluo.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.icboluo.common.serializer.ArchivesFlatteningSerializer;
import com.icboluo.common.serializer.BigDecimalRemoveZeroSerializer;
import com.icboluo.common.serializer.NumberDefaultZeroSerializer;
import com.icboluo.object.CodeName;
import com.icboluo.object.IdName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-09-10 20:22
 */
@Data
public class JacksonVO {
    private IdName level;

    @JsonSerialize(using = ArchivesFlatteningSerializer.class)
    private IdName status;

    @JsonSerialize(using = ArchivesFlatteningSerializer.class)
    private CodeName country;

    private BigDecimal total;

    @JsonSerialize(using = BigDecimalRemoveZeroSerializer.class)
    private BigDecimal avg;

    /**
     * 这里加这个 using 会直接报错
     */
//    @JsonSerialize(using = ArchivesFlatteningSerializer.class)
    private List<CodeName> countryList;
    /**
     * 状态列表，容器类应该使用这个属性
     */
    @JsonSerialize(contentUsing = ArchivesFlatteningSerializer.class)
    private List<IdName> statusList;
    /**
     * 这个需要设置为 nullsUsing才能起作用
     */
    @JsonSerialize(nullsUsing = NumberDefaultZeroSerializer.class)
    private Integer zero;
}
