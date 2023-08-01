package com.icboluo.common;


import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author icboluo
 * @since 2021-15-13 23:15
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (ObjectUtils.isEmpty(sra)) {
//            return false;
//        }
//        HttpServletRequest request = sra.getRequest();
//        Object attribute = request.getAttribute(ResponseResultInterceptor.resAnno);
//        ResponseResult responseResultAnn = (ResponseResult) attribute;
//        request.removeAttribute(ResponseResultInterceptor.resAnno);
//        return responseResultAnn != null;
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof IcBoLuoException exceptionBody) {
            return R.error(exceptionBody);
        }
//       已经是Response类型的不做处理，全局异常处理结果就是Response
//        抛异常的时候会先进行全局异常处理，然后才是返回值处理
        if (body instanceof Response bodyRes) {
            return bodyRes;
        }
//        String类型需要修改 content/type
//        方案1：修改请求路径 RequestMapping(produces = "application/json; charset=UTF-8")
//        方案2：删除 StringHttpMessageConverter 已采用/
//        方案3：在这里改一下转换器指向/并没有什么用
        return R.correct(body);
    }
}
