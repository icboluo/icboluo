package com.icboluo;

import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 拦截异常并统一处理
 *
 * @author icboluo
 */
@RestControllerAdvice({"com.icboluo.note"})
@Slf4j
public class GlobalControllerExceptionHandler {
    /**
     * 默认的异常处理方法，如果 e 有注解 @ResponseStatus 注解，则继续抛出，让框架处理
     *
     * @param request 请求信息
     * @param e 异常信息
     * @return 失败的响应信息
     * @throws Exception {@code e}
     */
    @ExceptionHandler(value = Exception.class)
    public Response defaultExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        printLog(request, e);
        //如果异常上已经有 @ResponseStatus 注解，则让框架处理
        if (Objects.nonNull(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class))) {
            throw e;
        }
        String msg = Objects.isNull(e.getMessage()) ? "操作失败，但没有失败消息" : e.getMessage();
        return R.error(500, msg);
    }

    /**
     * 异常处理,符合就近原则，先处理具体的异常，不能识别交给较大的异常
     *
     * @param request 请求信息
     * @param e 异常信息
     * @return 失败的响应信息
     */
    @ExceptionHandler(value = {IcBoLuoException.class})
    public Response noteExceptionHandler(HttpServletRequest request, Exception e) {
        printLog(request, e);
        return R.error(500,e);
    }


    /**
     * 打印 {@code request} 信息 和 {@code e} 信息
     *
     * @param request 请求信息
     * @param e       错误信息
     */
    private void printLog(HttpServletRequest request, Throwable e) {
        if (log.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append("\nURL:").append(request.getRequestURL().toString()).append('\n');
            builder.append("Method:").append(request.getMethod()).append('\n');
            builder.append("Headers:\n");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                builder.append(name).append(":").append(request.getHeader(name)).append('\n');
            }
            builder.append("Cookies:\n");
            Cookie[] cookies = request.getCookies();
            if (Objects.nonNull(cookies)) {
                for (Cookie cookie : cookies) {
                    builder.append(cookie.getName()).append(":").append(cookie.getValue()).append('\n');
                }
            }
            log.debug("{}", builder.toString());
        }
        log.error("{}", e.getMessage(), e);
    }
}
