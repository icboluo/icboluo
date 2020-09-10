
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
import java.util.List;

/**
 * 公共返回值
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
            List arrayListData = (ArrayList) data;
            return new PageInfo(arrayListData);
        } else {
            throw new IcBoLuoException("干嘛将不是collection的数据传进来让构建pageInfo?");
        }
    }

    private boolean isCollection(Object object) {
        return object instanceof Collection;
    }

}
