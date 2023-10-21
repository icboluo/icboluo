package com.icboluo.entity.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author icboluo
 */
@Data
public class ColumnDOWithBLOBs extends ColumnDO implements Serializable {
    private String columnDefault;

    private String columnType;

    @Serial
    private static final long serialVersionUID = 1L;
}
