package com.icboluo.common;

import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.IcBoLuoI18nException;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 拦截异常并统一处理
 *
 * @author icboluo
 */
@RestControllerAdvice({"com.icboluo"})
@Slf4j
public class GlobalControllerExceptionHandler {

    @Resource
    private HttpServletRequest request;

    @Resource
    private MessageSource messageSource;

    /**
     * 异常处理,符合就近原则，先处理具体的异常，不能识别交给较大的异常
     *
     * @param e 异常信息
     * @return 失败的响应信息
     */
    @ExceptionHandler(value = {IcBoLuoException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @SuppressWarnings("all")
    public Response icBoLuoExceptionHandler(IcBoLuoException e) {
        printLog(e);
        // response.setStatus(500); // 也可以使用这样的方式设置状态码，但是状态码只有200、400、500之类的有效，其他的都没用
        return R.error(500, e.getMessage());
    }

    /**
     * 异常处理,符合就近原则，先处理具体的异常，不能识别交给较大的异常
     *
     * @param e 异常信息
     * @return 失败的响应信息
     */
    @ExceptionHandler(value = {IcBoLuoI18nException.class})
    public Response icBoLuoI18nExceptionHandler(IcBoLuoI18nException e) {
        printLog(e);
        String message = messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
        return R.error(500, message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        printLog(e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String msg = fieldErrors.stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String defaultMessage = fieldError.getDefaultMessage();
                    assert defaultMessage != null;
                    String message = messageSource.getMessage(defaultMessage, null, LocaleContextHolder.getLocale());
                    return field + " " + message;
                }).collect(Collectors.joining(";"));
        return R.error(msg);
    }

    /**
     * @param e exception
     * @return msg
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationExceptionHandler(ConstraintViolationException e) {
        printLog(e);
        return R.error(e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Response runtimeExceptionHandler(RuntimeException e) {
        printLog(e);
        return R.error(ReEnum.UNEXPECTED_EXCEPTION);
    }

    /**
     * 能跑到这里，没有被runtime捕获到，应该属于受检异常
     * <p>
     * 默认的异常处理方法，如果 e 有注解 @ResponseStatus 注解，则继续抛出，让框架处理
     *
     * @param e 异常信息
     * @return 失败的响应信息
     * @throws Exception {@code e} 最大异常
     */
    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(Exception e) throws Exception {
        printLog(e);
        //如果异常上已经有 @ResponseStatus 注解，则让框架处理
        if (Objects.nonNull(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class))) {
            throw e;
        }
        String msg = Objects.isNull(e.getMessage()) ? "操作失败，但没有失败消息" : e.getMessage();
        return R.error(500, msg);
    }


    /**
     * 打印 {@code request} 信息 和 {@code e} 信息
     *
     * @param e 错误信息
     */
    private void printLog(Throwable e) {
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
            log.debug("{}", builder);
        }
        log.error("{}", e.getMessage(), e);
    }
}
