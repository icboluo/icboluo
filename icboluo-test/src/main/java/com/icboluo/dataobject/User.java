package com.icboluo.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    /**
     * 用包装类解决数据库查询出来为null问题
     */
    private String username;
    private String password;
}
