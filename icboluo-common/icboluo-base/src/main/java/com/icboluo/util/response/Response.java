
package com.icboluo.util.response;

import com.icboluo.enumerate.ReEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.io.Serial;
import java.io.Serializable;

/**
 * 公共返回值
 * 其他服务引用这个类，会导致page helper找不到
 * 使用PageHelper在【服务层】进行分页查询时返回的结果是一个Page对象，在服务发布于获取的序列化与反序列化
 * 中会去找Page，然而Page对象继承ArrayList，在最终接收时这个返回对象时，List并没有报错，但是在
 * 【引用服务的web层】并没有PageHelper的jar包，所以发出警告；
 * 　　需要在web层的pom.xml文件中添加依赖
 * 　　<dependency>
 * 　　<groupId>com.github.pagehelper</groupId>
 * 　　<artifactId>pagehelper</artifactId>
 * 　　</dependency>
 * <p>
 * 可是导包了又要依赖数据库，所以需要分离
 * <p>
 * optional导致依赖不能被子项目所共享，删除optional，子项目可以使用到就不会报错
 *
 * @author icboluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 3840949955410953654L;

    public static final String ERROR_CODE = "500";
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;

    protected Response(ReEnum reEnum) {
        this.code = reEnum.getCode();
        this.message = reEnum.getMessage();
    }

    @Transient
    public boolean isSuccessful() {
        return code.matches("^(2\\d{2}|0)$");
    }
}
