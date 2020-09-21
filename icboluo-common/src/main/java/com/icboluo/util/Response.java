
package com.icboluo.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icboluo.common.ReEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
 *
 * @author icboluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unchecked,rawtypes")
public class Response implements Serializable {
    private static final long serialVersionUID = 3840949955410953654L;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;

    public Response(ReEnum reEnum) {
        this.code = reEnum.getCode();
        this.message = reEnum.getMessage();
    }

    /**
     * 提供response对象的分页构建功能，
     * 只支持常见的数据格式，其他自行实现
     *
     * @return response对象
     */
    public Response page() {
        //强转成为本类的一个对象
        SingleResponse response = null;
        if (this instanceof SingleResponse) {
            response = (SingleResponse) this;
        }
        if (response == null) {
            throw new IcBoLuoException("走这里就完蛋了");
        }
        //判断对象中的数据类型并进行分页返回值构建
        Object oldData = response.getData();
        JSONObject newData = new JSONObject();
        PageInfo pageInfo;
        if (this.isCollection(oldData)) {
            pageInfo = this.buildPageInfo(oldData);
            response.setData(pageInfo);
        } else if (oldData instanceof JSONObject) {
            JSONObject jsonObjectData = (JSONObject) oldData;
            int pageTimeCount = 0;
            for (String s : jsonObjectData.keySet()) {
                if (s.contains("list")) {
                    Object v = jsonObjectData.get(s);
                    pageInfo = this.buildPageInfo(v);
                    newData.put(s, pageInfo);
                    pageTimeCount++;
                }
            }
            if (pageTimeCount == 0) {
                throw new IcBoLuoException("返回值中好像没有容器数据，分页失效");
            }
        } else {
            throw new IcBoLuoException("我分不了页，自己手动去分");
        }
        response.setData(newData);
        return response;
    }

    private PageInfo buildPageInfo(Object data) {
        if (this.isCollection(data)) {
//            new PageInfo((ArrayList) data) 也可以
            return PageInfo.of((ArrayList) data);
        } else {
            throw new IcBoLuoException("干嘛将不是collection的数据传进来让构建pageInfo?");
        }
    }

    private boolean isCollection(Object object) {
        return object instanceof Collection;
    }

}
