package com.icboluo.object.dataobject;

import lombok.Data;

import java.io.Serializable;

/**
 * @author icboluo
 */
@Data
public class ColumnDOWithBLOBs extends ColumnDO implements Serializable {
    private String columnDefault;

    private String columnType;

    private static final long serialVersionUID = 1L;
}
