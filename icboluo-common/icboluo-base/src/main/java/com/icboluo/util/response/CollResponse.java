package com.icboluo.util.response;

import com.github.pagehelper.PageInfo;
import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.BeanHelper;
import lombok.Data;

import java.util.Collection;

/**
 * @author icboluo
 * @date 2021-14-18 21:14
 */
@Data
public class CollResponse<T> extends Response {

    /**
     * 数据
     */
    private Collection<T> data;

    public CollResponse(ReEnum reEnum) {
        super(reEnum);
    }

    public CollResponse(Collection<T> data, ReEnum reEnum) {
        super(reEnum);
        this.data = data;
    }

    public CollResponse(Integer code, String message) {
        super(code, message);
    }

    @Override
    public Response page() {
        //判断对象中的数据类型并进行分页返回值构建
        PageInfo<T> pageInfo = BeanHelper.fakePage(data);
        return R.correct(pageInfo);
    }
}
